package com.web.b1.items;

import com.web.b1.config.BatchConfig;
import com.web.b1.model.MovieRow;
import com.web.b1.repository.MovieRepository;
import com.web.b1.repository.MovieRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MovieRowWriter implements ItemWriter<MovieRow> {

    private static final Logger logger = LoggerFactory.getLogger(MovieRowWriter.class);

    @Autowired
    private MovieRepositoryImpl movieRepository;

    private boolean flag = false;
    @Override
    @Transactional
    public void write(List<? extends MovieRow> listMovieRow) {
        if (!flag) {
            movieRepository.truncateStg();
            flag = true;
        }
        movieRepository.saveAll((List<MovieRow>) listMovieRow);
    }
}
