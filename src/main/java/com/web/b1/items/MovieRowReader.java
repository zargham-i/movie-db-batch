package com.web.b1.items;

import com.google.gson.Gson;
import com.web.b1.model.MovieRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.zip.GZIPInputStream;
import java.nio.charset.StandardCharsets;

public class MovieRowReader implements ItemReader<MovieRow> {

    private static final Logger logger = LoggerFactory.getLogger(MovieRowReader.class);

    private BufferedReader buffered;
    private Gson gson;
    private boolean flag = false;
    @Override
    public MovieRow read() throws Exception {

        /*
         * Flow:
         * 1. Read data into a stream from tmdb site -> gzip stream to extract entire data onto the disk
         * 2. See if it's worth reading the json file directly from the byte array, or it's better to close the input stream and then read from the disk line by line.
         *
         * Alternative flow:
         * 1. Read data slowly using spring webclient.
         * 2. Start extracting at the same time.
         * 3.
         *
         */
        if (flag == false)
            downloadFile();

        String movieRowString = buffered.readLine();
        if (movieRowString == null) {
            buffered.close();
            return null;
        }
        else {
            MovieRow movieRow = gson.fromJson(movieRowString, MovieRow.class);
            return movieRow;
        }
    }

    private void downloadFile() throws IOException {

        LocalDate localDate = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM_dd_YYYY");

        String fileName = "movie_ids_" + formatter.format(localDate) + ".json";
        String urlStr = "http://files.tmdb.org/p/exports/" + fileName + ".gz";
        String filePath = "";

        URL url = new URL(urlStr);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        InputStream gzipExtractor = new GZIPInputStream(httpConnection.getInputStream());
        Reader decoder = new InputStreamReader(gzipExtractor, StandardCharsets.UTF_8);
        buffered = new BufferedReader(decoder);
        gson = new Gson();
        flag = true;
    }

}
