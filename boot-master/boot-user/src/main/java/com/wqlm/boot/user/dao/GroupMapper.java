package com.wqlm.boot.user.dao;

import com.wqlm.boot.user.po.Group;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface GroupMapper extends Mapper<Group> {
}