package de.feedhelfer.app.entity.restItems;

/**
 * Created by cernota on 26.12.15.
 */
public class FeedRestItem {
    public long id;
    public String url;
    public String lastread;
    public String status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastread() {
        return lastread;
    }

    public void setLastread(String lastread) {
        this.lastread = lastread;
    }
}
