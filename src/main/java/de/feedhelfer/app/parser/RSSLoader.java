package de.feedhelfer.app.parser;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cernota on 23.12.15.
 */
public class RSSLoader {
    Logger log = LoggerFactory.getLogger(RSSLoader.class);
    private Document doc;
    public RSSLoader(String url){
        try {
            log.info("Laden von RSS Feed gestartet");
            SAXBuilder builder = new SAXBuilder();

            try {

                Document document = (Document) builder.build(new URL(url).openStream());
                doc = document;


            } catch (IOException io) {
                System.out.println(io.getMessage());
            }
            log.info("Laden von RSS Feed beendet");


        } catch (Exception e) {
            log.error("Fehler beim Laden des RSS Files: ", e);
            e.printStackTrace();
        }
    }

    public Document getDoc(){
        return doc;
    }
}
