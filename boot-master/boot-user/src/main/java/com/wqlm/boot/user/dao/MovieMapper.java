package com.wqlm.boot.user.dao;

import com.wqlm.boot.user.po.Movie;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MovieMapper extends Mapper<Movie> {
}