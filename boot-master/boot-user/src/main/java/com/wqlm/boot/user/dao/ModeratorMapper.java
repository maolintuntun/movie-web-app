package com.wqlm.boot.user.dao;

import com.wqlm.boot.user.po.Moderator;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ModeratorMapper extends Mapper<Moderator> {
}