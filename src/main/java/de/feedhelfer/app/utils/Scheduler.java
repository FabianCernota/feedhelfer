package de.feedhelfer.app.utils;

import de.feedhelfer.app.entity.RSSFeed;
import de.feedhelfer.app.parser.Parser;
import de.feedhelfer.app.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.List;

/**
 * Created by cernota on 22.12.15.
 */
@Controller
public class Scheduler {
    Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    ApplicationContext ctx;
    @Scheduled(fixedDelay = 60000)
    public void run(){
        log.info("HallO");


        List<RSSFeed> rssFeeds = feedRepository.findAll();
        System.out.println(rssFeeds.size());
        for(RSSFeed feed : rssFeeds){
            System.out.println(feed.getUrl());

            Parser parser = (Parser) ctx.getBean("parser");
            parser.setRssFeed(feed);
            parser.start();
        }


    }


}

