package com.wqlm.boot.user.dao;

import com.wqlm.boot.user.po.EventHasMovie;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface EventHasMovieMapper extends Mapper<EventHasMovie> {
}