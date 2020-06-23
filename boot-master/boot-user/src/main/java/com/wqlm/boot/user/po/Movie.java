package com.wqlm.boot.user.po;

import javax.persistence.*;

@Table(name = "movie")
public class Movie {
    @Id
    private String name;

    private String review;

    private String trailer;

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
     * @return review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review
     */
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }

    /**
     * @return trailer
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * @param trailer
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer == null ? null : trailer.trim();
    }
}