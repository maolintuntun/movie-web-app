package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "vote")
public class Vote {
    @Id
    @Column(name = "event_name")
    private String eventName;

    @Id
    @Column(name = "movie_name")
    private String movieName;

    @Id
    @Column(name = "watcher_account")
    private String watcherAccount;

    /**
     * @return event_name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    /**
     * @return movie_name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * @param movieName
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }

    /**
     * @return watcher_account
     */
    public String getWatcherAccount() {
        return watcherAccount;
    }

    /**
     * @param watcherAccount
     */
    public void setWatcherAccount(String watcherAccount) {
        this.watcherAccount = watcherAccount == null ? null : watcherAccount.trim();
    }
}