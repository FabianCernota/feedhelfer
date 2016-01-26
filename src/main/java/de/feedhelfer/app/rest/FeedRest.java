package de.feedhelfer.app.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.feedhelfer.app.entity.Feed;
import de.feedhelfer.app.entity.restItems.FeedRestItem;
import de.feedhelfer.app.repository.FeedRepository;
import de.feedhelfer.app.repository.UserRepository;
import facebook4j.internal.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cernota on 26.12.15.
 */
@RestController
@RequestMapping(value = "/api/feeds")
@JsonAutoDetect
public class FeedRest {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<FeedRestItem> getFeedList(){
        List<Feed> feeds = feedRepository.findAll();
        List<FeedRestItem> response = new ArrayList<FeedRestItem>();
        for(Feed f : feeds){
            FeedRestItem item = new FeedRestItem();
            item.setId(f.getId());
            item.setUrl(f.getUrl());
            item.setLastread(f.getLastread());
            item.setStatus("alles ok");
            feedRepository.findFirstOrderByidDesc();
            response.add(item);
        }

        System.out.println(feeds);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Feed createFeed(@RequestBody Feed feed){
        Feed f = new Feed();
       return null;
    }
}
