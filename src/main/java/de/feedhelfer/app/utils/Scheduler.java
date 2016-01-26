package de.feedhelfer.app.utils;

import de.feedhelfer.app.entity.Feed;
import de.feedhelfer.app.parser.Parser;
import de.feedhelfer.app.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

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


        List<Feed> feeds = feedRepository.findAll();
        System.out.println(feeds.size());
        for(Feed feed : feeds){
            System.out.println(feed.getUrl());

            Parser parser = (Parser) ctx.getBean("parser");
            parser.setFeed(feed);
            parser.start();
        }


    }


}

