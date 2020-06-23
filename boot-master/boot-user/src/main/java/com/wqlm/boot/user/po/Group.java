package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "groupzu")
public class Group {
    @Id
    private String name;

    @Column(name = "moderator_account")
    private String moderatorAccount;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return moderator_account
     */
    public String getModeratorAccount() {
        return moderatorAccount;
    }

    /**
     * @param moderatorAccount
     */
    public void setModeratorAccount(String moderatorAccount) {
        this.moderatorAccount = moderatorAccount == null ? null : moderatorAccount.trim();
    }
}