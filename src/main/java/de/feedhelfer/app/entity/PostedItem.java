package de.feedhelfer.app.entity;

import javax.persistence.*;

/**
 * Created by cernota on 23.12.15.
 */
@Entity
@Table(name = "item_posted")
public class PostedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long id_feed;
    private String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_feed() {
        return id_feed;
    }

    public void setId_feed(long id_feed) {
        this.id_feed = id_feed;
    }
}
