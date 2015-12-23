package de.feedhelfer.app.parser;

import de.feedhelfer.app.entity.PostedItem;
import de.feedhelfer.app.entity.RSSFeed;
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
    private RSSFeed rssFeed;
    public Parser(){

    }
    @Override
    public void run() {

        RSSLoader rssLoader = new RSSLoader(rssFeed.getUrl());

        Document doc = rssLoader.getDoc();

        List<Element> items = doc.getRootElement().getChild("channel").getChildren("item");
        for(Element e : items){
            if(rssFeed.getLastread() != null){

            }else{
                PostedItem postedItem = new PostedItem();
                postedItem.setId_feed(rssFeed.getId());
                postedItem.setGuid(e.getChild("guid").getText());
                postedItemRepository.save(postedItem);
                postedItemRepository.flush();
            }


        }

        rssFeed.setLastread(new Timestamp(System.currentTimeMillis()).toString());
        feedRepository.updateLastread(new Timestamp(System.currentTimeMillis()).toString(), rssFeed.getId());
    }

    public RSSFeed getRssFeed() {
        return rssFeed;
    }

    public void setRssFeed(RSSFeed rssFeed) {
        this.rssFeed = rssFeed;
    }
}
