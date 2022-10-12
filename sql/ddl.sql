DROP TABLE IF EXISTS MOVIE;

/

CREATE TABLE MOVIE
(
    MOVIE_ID int PRIMARY KEY,
    TMDB_ID int NOT NULL UNIQUE,
    TITLE varchar(255),
    POPULARITY numeric(10, 4),
    IMDB_ID varchar(15),
    BACKDROP_IMG_URL varchar(500),
    BUDGET int,
    --BELONGS_TO_COLLECTION varchar(1), not mapped for now
    HOMEPAGE_URL varchar(5000),
    LANGUAGE varchar(255),
    ORIGINAL_TITLE varchar(255),
    OVERVIEW varchar(10000),
    POSTER_URL varchar(255),
    RELEASE_DATE date,
    REVENUE int,
    RUNTIME int,
    ADULT_IND varchar(1),
    VOTE_AVG numeric(10,4),
    VOTE_COUNT int,
    STATUS varchar(50),
    VIDEO_IND int
);

/*
CREATE TABLE MOVIE
(
    MOVIE_ID int PRIMARY KEY,
    IMDB_ID varchar(15),
    TMDB_ID int NOT NULL UNIQUE,
    TITLE varchar(255),
    IMDB_RATING numeric(4, 2),
    IMDB_VOTES int,
    ROTTEN_RATING int,
    METACRITIC_RATING varchar(3),
    RUNTIME varchar(9),
    LANGUAGE varchar(255),
    YEAR varchar(4),
    COUNTRY varchar(100),
    POPULARITY numeric(10, 4),
    POSTER varchar(2000),
    ORIGINAL_PRICE NUMERIC(3,2),
    CURRENT_PRICE NUMERIC(3,2),
    AGE_RATING varchar(10),
    RELEASED date,
    WRITER varchar(100),
    ACTORS varchar(500),
    PLOT varchar(10000),
    GENRE varchar(255),
    BUDGET int,
    BOX_OFFICE int,
    AWARDS varchar(255),
    WEBSITE varchar(255),
    DVD DATE,
    DIRECTOR varchar(255)
);
*/

/

DROP SEQUENCE IF EXISTS MOVIE_ID_SEQ;

/

CREATE SEQUENCE MOVIE_ID_SEQ
    INCREMENT 1
START 1
OWNED BY movie.MOVIE_ID;

/

CREATE TABLE MOVIE_STAGING
(
    TMDB_ID int NOT NULL UNIQUE,
    VIDEO_IND varchar(1),
    POPULARITY numeric(10, 4),
    TITLE varchar(255),
    ADULT varchar(1)
);

/

INSERT INTO MOVIE_STAGING (TMDB_ID, VIDEO_IND, POPULARITY, TITLE, ADULT)
VALUES (?,?,?,?,?);

/

TRUNCATE TABLE movie_staging;

/

select count(*) from movie_staging; --744175

/

select count(*) from (
                         select TMDB_ID, count(*) from movie_staging
                         group by TMDB_ID
                         having count(tmdb_id) > 0) as overall; --744175

/

CREATE OR REPLACE FUNCTION cleanMovieStg()
RETURNS VOID AS $$
DECLARE
_query  text;
BEGIN

_query := 'TRUNCATE TABLE movie_staging';

BEGIN
        EXECUTE _query;
        RAISE NOTICE 'QUERY EXECUTED SUCCESSFULLY';
EXCEPTION
        WHEN OTHERS
        THEN
            RAISE NOTICE 'ERROR WHILE EXECUTING THE QUERY: % %', SQLSTATE, SQLERRM;
END;

    RETURN;

END $$ LANGUAGE plpgsql;

/

select cleanMovieStg();

/

select * from movie_staging order by POPULARITY desc;
select * from movie_staging where TMDB_ID = 550;
SELECT * FROM movie_staging order by POPULARITY desc LIMIT 5000;

/