package com.wqlm.boot.user.dao;

import com.wqlm.boot.user.po.Vote;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface VoteMapper extends Mapper<Vote> {
}