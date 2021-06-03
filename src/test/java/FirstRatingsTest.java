import models.IRater;
import models.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class FirstRatingsTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    FirstRatings firstRatingsShort;
    ArrayList<Movie> shortMovieCollection;
    ArrayList<IRater> shortMovieCollectionRatings;

    FirstRatings firstRatingsFull;
    ArrayList<Movie> fullMovieCollection;
    ArrayList<IRater> fullMovieCollectionRatings;

    @BeforeAll
    void setUp() throws IOException
    {
        String shortCSV = "data/ratedmovies_short.csv";
        String fullCSV = "data/ratedmoviesfull.csv";
        firstRatingsShort = new FirstRatings();
        shortMovieCollection = firstRatingsShort.loadMovies( shortCSV );
        shortMovieCollectionRatings = firstRatingsShort.loadRaters( "data/ratings_short.csv" );

        firstRatingsFull = new FirstRatings();
        fullMovieCollection = firstRatingsFull.loadMovies( fullCSV );
        fullMovieCollectionRatings = firstRatingsFull.loadRaters( "data/ratings.csv" );
    }

    @AfterAll
    public void tearDown()
    {
        firstRatingsShort = null;
        firstRatingsFull = null;
        shortMovieCollection = null;
        fullMovieCollection = null;
        shortMovieCollectionRatings = null;
        fullMovieCollectionRatings = null;


    }

    @BeforeEach
    public void setUpStreams()
    {
        System.setOut( new PrintStream( outContent ) );
        System.setErr( new PrintStream( errContent ) );
    }

    @AfterEach
    public void restoreStreams()
    {
        System.setOut( originalOut );
        System.setErr( originalErr );
    }

    @Test
    public void testLoadMovies()
    {
        assertEquals( 5, shortMovieCollection.size() );
        assertEquals( 3143, fullMovieCollection.size() );
    }

    @Test
    public void testMaximumNumberOfMoviesDirectedByAnyDirector()
    {
        assertEquals( 1, firstRatingsShort.maximumNumberOfMoviesByAnyDirector() );
        assertTrue( outContent.toString().contains( "Charles Chaplin directed more films than any other director" ) );

        assertEquals( 23, firstRatingsFull.maximumNumberOfMoviesByAnyDirector() );
        assertTrue( outContent.toString().contains( "Woody Allen directed more films than any other director" ) );

    }


    @Test
    public void testCountMoviesOfGenre()
    {
        String genre = "Comedy";
        int numOfMovies = firstRatingsFull.getNumMoviesForGenre( genre );
        assertEquals( 960, numOfMovies );

        numOfMovies = firstRatingsShort.getNumMoviesForGenre( genre );
        assertEquals( 1, numOfMovies );

    }

    @Test
    public void testGetNumOfMoviesLongerThan(){
        int minMinutes = 150;
        int numberOfMovies = firstRatingsShort.getNumOfMoviesLongerThan( minMinutes );
        assertEquals( 2, numberOfMovies );

        numberOfMovies = firstRatingsFull.getNumOfMoviesLongerThan( minMinutes );
        assertEquals( 132, numberOfMovies );
    }

    @Test
    public void testLoadRaters()
    {
        assertEquals( 5, shortMovieCollectionRatings.size() );
        assertEquals(  1048 , fullMovieCollectionRatings.size());
    }

    @Test
    public void testNumofRatingsForParticularRater()
    {
        String raterId = "2";
        int numberOfRatings = firstRatingsShort.getNumOfRatingsForRater(raterId);
        assertEquals( 3, numberOfRatings);

        raterId = "193";
        numberOfRatings = firstRatingsFull.getNumOfRatingsForRater(raterId);
        assertEquals( 119, numberOfRatings);
    }

    @Test
    public void testMaximumNumOfRatingsByAnyRater()
    {

        int maximumNumberOfRatings = firstRatingsShort.getMaximumNumOfRatingsByAnyRater();
        assertEquals( 3, maximumNumberOfRatings );
        assertTrue( outContent.toString().contains( "2 rated the most number of movies" ) );
        assertTrue( outContent.toString().contains( "Raters with the same maximum number of ratings [2]" ) );

        maximumNumberOfRatings = firstRatingsFull.getMaximumNumOfRatingsByAnyRater();
        assertEquals( 314, maximumNumberOfRatings );
        assertTrue( outContent.toString().contains( "735 rated the most number of movies" ) );
        assertTrue( outContent.toString().contains( "Raters with the same maximum number of ratings [735]" ) );
    }


    @Test
    public void testNumOfRatingsForMovie()
    {
        String movieId = "1798709";
        int numberOfRatings = firstRatingsShort.getNumOfRatingsForMovie( movieId );
        assertEquals( 5, numberOfRatings );
        assertTrue( outContent.toString().contains( "4 unique rated movies" ) );


        numberOfRatings = firstRatingsFull.getNumOfRatingsForMovie( movieId );
        assertEquals( 1048, numberOfRatings );
        assertTrue( outContent.toString().contains( "3143 unique rated movies" ) );
    }

}