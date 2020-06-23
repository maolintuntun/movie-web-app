package com.wqlm.boot.user.vo;
import lombok.Data;

@Data
public class MoviesVO {
    private String name;

    private String review;

    private String trailer;

    private boolean isVote;
}
