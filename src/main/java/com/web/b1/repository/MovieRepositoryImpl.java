package com.web.b1.repository;

import com.web.b1.model.MovieRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//Use function/stored proc to truncate table beforehand
@Repository
public class MovieRepositoryImpl implements MovieRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    /*public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    @Override
    @Transactional
    public void saveAll(List<MovieRow> movies) {
        //final int batchSize = 1000;

        String instQuery = "INSERT INTO MOVIE_STAGING (TMDB_ID, POPULARITY, TITLE) VALUES (?,?,?)";

        jdbcTemplate.batchUpdate(instQuery,
            movies,
            1000,
            (PreparedStatement preparedStatement, MovieRow movie) -> {
                preparedStatement.setInt(1, movie.getId());
                preparedStatement.setDouble(2, movie.getPopularity());
                preparedStatement.setString(3, movie.getOriginal_title());
            });
    }

    @Override
    @Transactional
    public void truncateStg() {

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("cleanMovieStg");
        simpleJdbcCall.compile();
        //simpleJdbcCall.executeFunction();
        Map<String, Object> result = null;
        result = simpleJdbcCall.execute();
        return;
    }
}
