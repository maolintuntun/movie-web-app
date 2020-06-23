package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "event_has_watcher")
public class EventHasWatcher {
    @Id
    @Column(name = "event_name")
    private String eventName;

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