package com.web.b1.repository;

import com.web.b1.model.MovieRow;

import java.util.List;

public interface MovieRepository {
    void saveAll(List<MovieRow> movies);
}
