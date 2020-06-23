package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "event")
public class Event {
    @Id
    private String name;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "vote_start")
    private String voteStart;

    @Column(name = "vote_end")
    private String voteEnd;

    private String winner;

    @Column(name = "group_name")
    private String groupName;

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
     * @return start_time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * @return end_time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * @return vote_start
     */
    public String getVoteStart() {
        return voteStart;
    }

    /**
     * @param voteStart
     */
    public void setVoteStart(String voteStart) {
        this.voteStart = voteStart == null ? null : voteStart.trim();
    }

    /**
     * @return vote_end
     */
    public String getVoteEnd() {
        return voteEnd;
    }

    /**
     * @param voteEnd
     */
    public void setVoteEnd(String voteEnd) {
        this.voteEnd = voteEnd == null ? null : voteEnd.trim();
    }

    /**
     * @return winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * @param winner
     */
    public void setWinner(String winner) {
        this.winner = winner == null ? null : winner.trim();
    }

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