package com.web.b1;

import com.web.b1.config.BatchConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.web")
@EnableBatchProcessing
@EnableCaching
public class ProjectB1Application {
	static final Logger log = LoggerFactory.getLogger(ProjectB1Application.class);
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProjectB1Application.class, args);
		int exitCode = SpringApplication.exit(context);
		System.exit(exitCode);
	}

}
/*
(scanBasePackages = "com.web.b1")
 */

/*
Next steps to building a database
	Map all rows from the staging table to the main table.
	Mapping process
		- First time mapping.
			- Copy all rows from stg table to main table
			-

		- Everyday mapping.
			- Insert TMDB IDS that don't exist in the main table.
			- Update all the popularity numbers for the other ones.

		- Mapping process can be merged into 1 where:
			- The function checks for a TMDB ID
				- If one doesn't exist, a new row is inserted with new data.
				- If one already exists, the popularity numbers are updated for that one.
				- The check being exclusive or inclusive won't matter in this case
	New indicator needed in main table: db-tracker
		(will track what movie we already have detailed data for)
		- Columns
			- TMDB_IND
			- Popularity
			- Detailed_IND

	Create table for genres, people, and production companies

	Step 1: Movie Staging Process
	Step 2: Mapping from staging to main table.
	Step 3: Find details for popular movies from the api (5000 movies to start). Eventual goal is to have all.
	Step 4: 
 */