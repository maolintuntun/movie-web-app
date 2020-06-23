package com.wqlm.boot.user.vo;
import com.wqlm.boot.user.po.Movie;
import lombok.Data;
import java.util.List;

@Data
public class PullVO {
    private List<Movie> movies;
}
