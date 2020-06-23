package com.wqlm.boot.user.service;

import com.wqlm.boot.user.dao.EventHasMovieMapper;
import com.wqlm.boot.user.dao.EventMapper;
import com.wqlm.boot.user.dao.MovieMapper;
import com.wqlm.boot.user.dao.VoteMapper;

import com.wqlm.boot.user.dto.*;
import com.wqlm.boot.user.enums.ApplicationEnum;
import com.wqlm.boot.user.exception.ApplicationException;
import com.wqlm.boot.user.po.Event;
import com.wqlm.boot.user.po.EventHasMovie;
import com.wqlm.boot.user.po.Movie;
import com.wqlm.boot.user.po.Vote;
import com.wqlm.boot.user.vo.GetMoviesVO;
import com.wqlm.boot.user.vo.MoviesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private EventHasMovieMapper eventHasMovieMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private EventMapper eventMapper;

    public GetMoviesVO getMovies(GetMoviesDTO dto){
        GetMoviesVO vo = new GetMoviesVO();

        EventHasMovie ev = new EventHasMovie();
        ev.setEventName(dto.getEventName());
        List<EventHasMovie> list = eventHasMovieMapper.select(ev);
        List<String> movieNameList = new ArrayList<>();
        for(EventHasMovie i : list){
            movieNameList.add(i.getMovieName());
        }
        List<MoviesVO> movies = new ArrayList<>();
        for(String movieName : movieNameList){
            MoviesVO mv = new MoviesVO();
            Movie m = new Movie();
            m.setName(movieName);
            mv.setName(movieName);
            Movie mResult = movieMapper.selectOne(m);
            mv.setReview(mResult.getReview());
            mv.setTrailer(mResult.getTrailer());
            Vote v = new Vote();
            v.setWatcherAccount(dto.getWatcherName());
            v.setMovieName(movieName);
            v.setEventName(dto.getEventName());
            Vote vResult = voteMapper.selectOne(v);
            if(vResult == null) mv.setVote(false);
            else mv.setVote(true);
            movies.add(mv);
        }
        vo.setMovieList(movies);
        return vo;
    }

    public boolean vote(VoteDTO dto) {
        //check vote date valid or not
        //get event table
        Event e = new Event();
        e.setName(dto.getEventName());
        Event event = eventMapper.selectOne(e);
        String voteEndTime = event.getVoteEnd();
        String voteStartTime = event.getVoteStart();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = formatter.format(currentTime);
        if(voteStartTime.compareTo(curTime) < 0 && voteEndTime.compareTo(curTime) > 0){
            Vote vote = new Vote();
            //change vote table
            boolean flag = true;
            vote.setEventName(dto.getEventName());
            vote.setMovieName(dto.getMovieName());
            vote.setWatcherAccount(dto.getWatcherName());
            flag = flag && 1 == voteMapper.insert(vote);

            //change event-has-movie
            EventHasMovie ev = new EventHasMovie();
            ev.setEventName(dto.getEventName());
            ev.setMovieName(dto.getMovieName());
            EventHasMovie needChangeVote = eventHasMovieMapper.selectOne(ev);
            int voteNum = needChangeVote.getUpVote();
            voteNum++;
            needChangeVote.setUpVote(voteNum);
            flag = flag && 1 == eventHasMovieMapper.updateByPrimaryKey(needChangeVote);
            return flag;
        }
        throw new ApplicationException(ApplicationEnum.INVALID_VOTE_TIME);
    }

    public boolean deleteVote(VoteDTO dto) {
        //check vote date valid or not
        //get event table
        Event e = new Event();
        e.setName(dto.getEventName());
        Event event = eventMapper.selectOne(e);
        String voteEndTime = event.getVoteEnd();
        String voteStartTime = event.getVoteStart();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = formatter.format(currentTime);
        if(voteStartTime.compareTo(curTime) < 0 && voteEndTime.compareTo(curTime) > 0){
            Vote vote = new Vote();
            //change vote table
            boolean flag = true;
            vote.setEventName(dto.getEventName());
            vote.setMovieName(dto.getMovieName());
            vote.setWatcherAccount(dto.getWatcherName());
            flag = flag && 1 == voteMapper.delete(vote);

            //change event-has-movie
            EventHasMovie ev = new EventHasMovie();
            ev.setEventName(dto.getEventName());
            ev.setMovieName(dto.getMovieName());
            EventHasMovie needChangeVote = eventHasMovieMapper.selectOne(ev);
            int voteNum = needChangeVote.getUpVote();
            voteNum--;
            needChangeVote.setUpVote(voteNum);
            flag = flag && 1 == eventHasMovieMapper.updateByPrimaryKey(needChangeVote);
            return flag;
        }
        throw new ApplicationException(ApplicationEnum.INVALID_VOTE_TIME);
    }

}
