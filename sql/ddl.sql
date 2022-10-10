DROP TABLE IF EXISTS movie;

CREATE TABLE movie
(
    MOVIE_ID int PRIMARY KEY,
    IMDB_ID varchar(15),
    TMDB_ID int NOT NULL,
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

DROP SEQUENCE IF EXISTS MOVIE_ID_SEQ;

CREATE SEQUENCE MOVIE_ID_SEQ
    INCREMENT 1
START 1
OWNED BY movie.MOVIE_ID;

