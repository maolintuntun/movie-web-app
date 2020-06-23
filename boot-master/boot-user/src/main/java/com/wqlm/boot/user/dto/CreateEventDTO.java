package com.wqlm.boot.user.dto;
import lombok.Data;

@Data
public class CreateEventDTO {
    private String eventName;

    private String startTime;

    private String endTime;

    private String voteStart;

    private String voteEnd;

    private String winner;

    private String groupName;

    private String moderatorAccount;
}
