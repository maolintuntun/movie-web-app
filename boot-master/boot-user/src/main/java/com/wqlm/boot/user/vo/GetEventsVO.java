package com.wqlm.boot.user.vo;
import com.wqlm.boot.user.po.Event;
import lombok.Data;

import java.util.List;

@Data
public class GetEventsVO {
    private List<Event> eventList;
}
