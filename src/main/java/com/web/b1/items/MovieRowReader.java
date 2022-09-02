package com.web.b1.items;

import com.web.b1.model.MovieRow;
import org.springframework.batch.item.ItemReader;

import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class MovieRowReader implements ItemReader<MovieRow> {

    private int counter = 0;

    @Override
    public MovieRow read() throws Exception {

        String date = "09_02_2022";
        String fileName = "movie_ids_" + date + ".json";
        String urlStr = "http://files.tmdb.org/p/exports/" + fileName + ".gz";
        String filePath = "";


        /*URL url = new URL(urlStr);
        HttpURLConnection someConnection = (HttpURLConnection) url.openConnection();
        GZIPInputStream someStream = new GZIPInputStream(someConnection.getInputStream());
        FileOutputStream someOutputStream = new FileOutputStream(fileName);
        byte[] results = new byte[1024];
        int count = someStream.read(results);
        while (count != -1) {
            byte[] result = Arrays.copyOf(results, count);
            someOutputStream.write(result);
            count = someStream.read(results);
        }
        someOutputStream.flush();
        someOutputStream.close();
        someStream.close();
        */

        /*
         * Flow:
         * 1. Read data into a stream from tmdb site -> gzip stream to extract entire data onto the disk
         * 2. See if it's worth reading the json file directly from the byte array or its better to close the input stream and then read from the disk line by line.
         */


        return null;
    }
}
