package de.feedhelfer.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Created by cernota on 22.12.15.
 */
@Controller
public class Scheduler {
    Logger log = LoggerFactory.getLogger(Scheduler.class);
    @Scheduled(fixedDelay = 60000)
    public void run(){
        log.info("HallO");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("http://www.konz.eu/vg_konz/Facebook/rss.xml").openStream());
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            log.info(doc.getDocumentElement().getNodeName());
            if (doc.hasChildNodes()) {
                NodeList guids = doc.getElementsByTagName("guid");
                for(int i = 0; i < 6; i++){
                    log.info(guids.item(i).getTextContent());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}

