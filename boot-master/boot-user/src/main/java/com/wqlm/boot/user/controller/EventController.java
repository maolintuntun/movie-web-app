package com.wqlm.boot.user.controller;

import com.wqlm.boot.user.dto.*;
import com.wqlm.boot.user.service.EventService;
import com.wqlm.boot.user.vo.GetEventVO;
import com.wqlm.boot.user.vo.GetEventsVO;
import com.wqlm.boot.user.vo.PullVO;
import com.wqlm.boot.user.vo.result.FailResult;
import com.wqlm.boot.user.vo.result.Result;
import com.wqlm.boot.user.vo.result.SuccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/event")
@Validated
public class EventController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventService eventService;


    @PostMapping("/create")
    public Result create(@Valid CreateEventDTO dto) {
        boolean result = eventService.create(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    /**
     * 获取用户信息
     * 参数通过url传递
     *
     * @param
     * @return
     */
    @GetMapping("/pull")
    public Result pull(@Valid PullDTO dto) {
        PullVO vo = eventService.pull(dto);
        for(int i = 0; i<vo.getMovies().size(); i++){
            System.out.println(vo.getMovies().get(i).getName());
        }
        return new SuccessResult<>(vo);
    }

    @PostMapping("/populate")
    public Result populate(@Valid PopulateDTO dto) {
        boolean result = eventService.populate(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @PostMapping("/disPopulate")
    public Result disPopulate(@Valid PopulateDTO dto) {
        boolean result = eventService.disPopulate(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @GetMapping("/getEvent")
    public Result getEvent(@Valid GetEventDTO dto) {
        GetEventVO vo = eventService.getEvent(dto);
        return new SuccessResult<>(vo);
    }

    @GetMapping("/getEvents")
    public Result getEvents(@Valid GetEventsDTO dto) {
        System.out.println(dto.getAccount());
        System.out.println(dto.getType());
        GetEventsVO vo = eventService.getEvents(dto);
        return new SuccessResult<>(vo);
    }
}
