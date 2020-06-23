package com.wqlm.boot.user.vo;

import lombok.Data;
import java.util.List;

@Data
public class GetEventVO {
    private String moderatorAccount;
    private String groupName;
    private String eventName;
    private String startTime;
    private String endTime;
    private String voteStart;
    private String voteEnd;
    private String winner;
    private List<String> movieList;
    private List<String> watcherList;
}
