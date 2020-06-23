package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "event_has_movie")
public class EventHasMovie {
    @Id
    @Column(name = "event_name")
    private String eventName;

    @Id
    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "up_vote")
    private Integer upVote;

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
     * @return up_vote
     */
    public Integer getUpVote() {
        return upVote;
    }

    /**
     * @param upVote
     */
    public void setUpVote(Integer upVote) {
        this.upVote = upVote;
    }
}