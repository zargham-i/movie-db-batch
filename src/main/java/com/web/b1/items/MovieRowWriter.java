package com.web.b1.items;

import com.web.b1.config.BatchConfig;
import com.web.b1.model.MovieRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class MovieRowWriter implements ItemWriter<MovieRow> {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Override
    public void write(List<? extends MovieRow> listMovieRow) {
        logger.info(String.valueOf(listMovieRow.size()));
        int counter = 0;
        while (counter < listMovieRow.size()) {
            int id = listMovieRow.get(counter).getId();
            boolean adult = listMovieRow.get(counter).isAdult();
            boolean video = listMovieRow.get(counter).isVideo();
            String originalTitle = listMovieRow.get(counter).getOriginal_title();
            Double popularity = listMovieRow.get(counter).getPopularity();
            logger.info("id: " + String.valueOf(id) + " original title: " + originalTitle + " popularity: " +
                popularity + " video: " + String.valueOf(video) + " adult: " + String.valueOf(adult));
            counter++;
        }
    }
}
