package de.feedhelfer.app.parser;

import de.feedhelfer.app.entity.PostedItem;
import de.feedhelfer.app.entity.Feed;
import de.feedhelfer.app.facebook.FacebookWrapper;
import de.feedhelfer.app.repository.FeedRepository;
import de.feedhelfer.app.repository.PostedItemRepository;
import org.jdom.Document;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cernota on 23.12.15.
 */
@Component
@Scope("prototype")
public class Parser extends Thread{
    @Autowired
    PostedItemRepository postedItemRepository;
    @Autowired
    FeedRepository feedRepository;

    Logger log = LoggerFactory.getLogger(Parser.class);
    private Feed feed;
    public Parser(){

    }
    @Override
    public void run() {

        RSSLoader rssLoader = new RSSLoader(feed.getUrl());

        Document doc = rssLoader.getDoc();

        List<Element> items = doc.getRootElement().getChild("channel").getChildren("item");
        for(Element e : items){
            if(feed.getLastread() != null){
                PostedItem poi = postedItemRepository.findByGuid(e.getChild("guid").getText());
                if(poi == null){
                    log.info("Posting: " + e.getChild("guid").getText());

                    FacebookWrapper fb = new FacebookWrapper();

                    PostedItem postedItem = new PostedItem();
                    postedItem.setId_feed(feed.getId());
                    postedItem.setGuid(e.getChild("guid").getText());
                    postedItemRepository.save(postedItem);
                    postedItemRepository.flush();
                }
            }else{
                PostedItem postedItem = new PostedItem();
                postedItem.setId_feed(feed.getId());
                postedItem.setGuid(e.getChild("guid").getText());
                postedItemRepository.save(postedItem);
                postedItemRepository.flush();
            }




        }

        feed.setLastread(new Timestamp(System.currentTimeMillis()).toString());
        feedRepository.save(feed);
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
