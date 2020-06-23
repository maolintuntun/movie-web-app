package com.wqlm.boot.user.dao;

import com.wqlm.boot.user.po.Event;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface EventMapper extends Mapper<Event> {
}