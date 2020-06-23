package com.wqlm.boot.user.service;

import com.wqlm.boot.user.dto.*;
import com.wqlm.boot.user.enums.ApplicationEnum;
import com.wqlm.boot.user.exception.ApplicationException;
import com.wqlm.boot.user.vo.GetEventVO;
import com.wqlm.boot.user.vo.GetEventsVO;
import com.wqlm.boot.user.vo.PullVO;
import com.wqlm.boot.user.dao.*;
import com.wqlm.boot.user.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private GroupHasWatcherMapper groupHasWatcherMapper;

    @Autowired
    private EventHasMovieMapper eventHasMovieMapper;

    @Autowired
    private EventHasWatcherMapper eventHasWatcherMapper;

    /**
     * 创建事件
     *
     * @param dto
     * @return
     */
    public boolean create(CreateEventDTO dto) {

        if (getEventByName(dto.getEventName()) != null) {
            //事件已存在
            throw new ApplicationException(ApplicationEnum.EVENT_NAME_REPETITION);
        }
        Event event = new Event();
        event.setEndTime(dto.getEndTime());
        event.setGroupName(dto.getGroupName());
        event.setModeratorAccount(dto.getModeratorAccount());
        event.setName(dto.getEventName());
        event.setStartTime(dto.getStartTime());
        event.setVoteEnd(dto.getVoteEnd());
        event.setVoteStart(dto.getVoteStart());
        event.setWinner("Not Selected");
        boolean result = true;
        result = result && (eventMapper.insert(event) == 1);

        GroupHasWatcher groupHasWatcher = new GroupHasWatcher();
        groupHasWatcher.setGroupName(dto.getGroupName());
        List<GroupHasWatcher> watcherList= groupHasWatcherMapper.select(groupHasWatcher);

        for(int i = 0; i<watcherList.size();i++){
            EventHasWatcher eventHasWatcher = new EventHasWatcher();
            eventHasWatcher.setEventName(dto.getEventName());
            eventHasWatcher.setWatcherAccount(watcherList.get(i).getWatcherAccount());
            result = result && ( eventHasWatcherMapper.insert(eventHasWatcher) == 1);
        }
        return result;
    }

    public boolean populate(PopulateDTO dto) {
        EventHasMovie ehm = new EventHasMovie();
        ehm.setEventName(dto.getEventName());
        ehm.setMovieName(dto.getMovieName());
        ehm.setUpVote(0);
        boolean res = true;
        res = res && 1 == eventHasMovieMapper.insert(ehm);
        return res;
    }

    public boolean disPopulate(PopulateDTO dto){
        EventHasMovie ehm = new EventHasMovie();
        ehm.setEventName(dto.getEventName());
        ehm.setMovieName(dto.getMovieName());
        ehm.setUpVote(0);
        boolean res = true;
        res = res && 1 == eventHasMovieMapper.delete(ehm);
        return res;
    }

    /**
     * 根据事件名查询事件
     *
     * @param name
     * @return
     */
    private Event getEventByName(String name) {
        Event event = new Event();
        event.setName(name);
        return eventMapper.selectOne(event);
    }

    public PullVO pull(PullDTO dto){
        List<Movie> movieList = movieMapper.selectAll();
        System.out.println("all movie list:"+movieList.size());
        List<Movie> list = new ArrayList<>();
        EventHasMovie ehm = new EventHasMovie();
        for(Movie m : movieList){
            ehm.setEventName(dto.getEventName());
            ehm.setMovieName(m.getName());
            if(eventHasMovieMapper.selectOne(ehm) == null) list.add(m);

        }
        PullVO vo = new PullVO();
        vo.setMovies(list);
        return vo;
    }

    public GetEventsVO getEvents(GetEventsDTO dto){
        String type = dto.getType();
        GetEventsVO vo = new GetEventsVO();
        List<Event> eventList = new ArrayList<>();
        if(type.equals("Moderator")){
            Event e = new Event();
            e.setModeratorAccount(dto.getAccount());
            eventList = eventMapper.select(e);
        }else if(type.equals("Watcher")){
            EventHasWatcher ew = new EventHasWatcher();
            ew.setWatcherAccount(dto.getAccount());
            List<EventHasWatcher> eventHasWatcherList = eventHasWatcherMapper.select(ew);
            for(EventHasWatcher i : eventHasWatcherList){
                Event e = new Event();
                e.setName(i.getEventName());
                List<Event> list = eventMapper.select(e);
                eventList.addAll(list);
            }
        }
        for(Event e : eventList){
            if(e.getWinner() != null) continue;
            int max = Integer.MIN_VALUE;
            String maxMovie = "";
            Date currentTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curTime = format.format(currentTime);
            if(curTime.compareTo(e.getVoteEnd()) > 0){
                EventHasMovie ehm = new EventHasMovie();
                ehm.setEventName(e.getName());
                List<EventHasMovie> mList = eventHasMovieMapper.select(ehm);
                for(EventHasMovie i : mList){
                    if(i.getUpVote() > max){
                        max = i.getUpVote();
                        maxMovie = i.getMovieName();
                    }
                }
                e.setWinner(maxMovie);
                eventMapper.updateByPrimaryKey(e);
            }
        }

        vo.setEventList(eventList);
        System.out.println("service over");
        return vo;
    }

    public GetEventVO getEvent(GetEventDTO dto){
        GetEventVO vo = new GetEventVO();
        Event e = new Event();
        e.setName(dto.getEventName());
        Event event = eventMapper.selectOne(e);
        vo.setStartTime(event.getStartTime());
        vo.setEndTime(event.getEndTime());
        vo.setVoteStart(event.getVoteStart());
        vo.setVoteEnd(event.getVoteEnd());
        vo.setGroupName(event.getGroupName());
        vo.setModeratorAccount(event.getModeratorAccount());
        vo.setWinner(event.getWinner());
        vo.setEventName(event.getName());

        EventHasMovie ev = new EventHasMovie();
        ev.setEventName(dto.getEventName());
        List<EventHasMovie> list = eventHasMovieMapper.select(ev);
        List<String> movieList = new ArrayList<>();
        for(EventHasMovie i : list){
            movieList.add(i.getMovieName());
        }
        vo.setMovieList(movieList);

        EventHasWatcher ew = new EventHasWatcher();
        ew.setEventName(dto.getEventName());
        List<EventHasWatcher> l = eventHasWatcherMapper.select(ew);
        List<String> watcherList = new ArrayList<>();
        for(EventHasWatcher i : l){
            watcherList.add(i.getWatcherAccount());
        }
        vo.setWatcherList(watcherList);
        return vo;
    }
}
