package com.wqlm.boot.user.controller;


import com.wqlm.boot.user.dto.CreateEventDTO;
import com.wqlm.boot.user.dto.GetMoviesDTO;
import com.wqlm.boot.user.dto.VoteDTO;
import com.wqlm.boot.user.service.MovieService;
import com.wqlm.boot.user.vo.GetMoviesVO;
import com.wqlm.boot.user.vo.result.FailResult;
import com.wqlm.boot.user.vo.result.Result;
import com.wqlm.boot.user.vo.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/movie")
@Validated
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/vote")
    public Result vote(@Valid VoteDTO dto)  {
        boolean result = movieService.vote(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @PostMapping("/deleteVote")
    public Result unVote(@Valid VoteDTO dto)  {
        boolean result = movieService.deleteVote(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @GetMapping("/getMovies")
    public Result getMovies(@Valid GetMoviesDTO dto) {
        GetMoviesVO vo = movieService.getMovies(dto);
        return new SuccessResult<>(vo);
    }

}
