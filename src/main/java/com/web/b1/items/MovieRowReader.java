package com.web.b1.items;

import com.web.b1.model.MovieRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.nio.charset.StandardCharsets;

public class MovieRowReader implements ItemReader<MovieRow> {

    private static final Logger logger = LoggerFactory.getLogger(MovieRowReader.class);
    private int counter = 0;

    @Override
    public MovieRow read() throws Exception {

        /*
         * Flow:
         * 1. Read data into a stream from tmdb site -> gzip stream to extract entire data onto the disk
         * 2. See if it's worth reading the json file directly from the byte array or its better to close the input stream and then read from the disk line by line.
         *
         * Alternative flow:
         * 1. Read data slowly using spring webclient.
         * 2. Start extracting at the same time.
         * 3.
         *
         */

        String date = "09_02_2022";
        String fileName = "movie_ids_" + date + ".json";
        String urlStr = "http://files.tmdb.org/p/exports/" + fileName + ".gz";
        String filePath = "";

        URL url = new URL(urlStr);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        GZIPInputStream gzipExtractor = new GZIPInputStream(httpConnection.getInputStream());
        FileOutputStream someOutputStream = new FileOutputStream(fileName);
        byte[] results = new byte[1024];
        int count = gzipExtractor.read(results);
        while (count != -1) {
            byte[] result = Arrays.copyOf(results, count);
            someOutputStream.write(result);
            logger.info("xd");
            count = gzipExtractor.read(results);
        }
        someOutputStream.flush();
        someOutputStream.close();
        gzipExtractor.close();


        return null;
    }
}
