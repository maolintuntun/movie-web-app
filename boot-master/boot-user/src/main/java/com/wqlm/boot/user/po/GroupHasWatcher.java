package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "group_has_watcher")
public class GroupHasWatcher {
    @Id
    @Column(name = "group_name")
    private String groupName;

    @Id
    @Column(name = "watcher_account")
    private String watcherAccount;

    /**
     * @return group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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