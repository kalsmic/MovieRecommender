import models.Movie;
import models.Rater;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class FirstRatingsTest
{

    FirstRatings firstRatingsShort;
    FirstRatings firstRatingsFull;
    ArrayList<Movie> shortMovieCollection;
    ArrayList<Movie> fullMovieCollection;
    ArrayList<Rater> shortMovieCollectionRatings;
    ArrayList<Rater> fullMovieCollectionRatings;

    @BeforeAll
    void setUp() throws IOException
    {
        String shortCSV = "data/ratedmovies_short.csv";
        String fullCSV = "data/ratedmoviesfull.csv";
        firstRatingsShort = new FirstRatings();
        firstRatingsFull = new FirstRatings();
        shortMovieCollection = firstRatingsShort.loadMovies( shortCSV );
        fullMovieCollection = firstRatingsFull.loadMovies( fullCSV );
        shortMovieCollectionRatings = firstRatingsShort.loadRaters( "data/ratings_short.csv" );
        fullMovieCollectionRatings = firstRatingsFull.loadRaters( "data/ratings.csv" );
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
        assertEquals( 23, firstRatingsFull.maximumNumberOfMoviesByAnyDirector() );
    }

    //    @Test
    //    public void testDirectorMovieCount(){
    //        HashMap<String , Integer> movieDirectors = firstRatingsFull.getDirectorMoviesCount();
    //        assertEquals( 23,movieDirectors.size() );
    //
    //
    //    }


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
    }

    @Test
    public void testMaximumNumOfRatingsByAnyRater()
    {

        int maximumNumberOfRatings = firstRatingsShort.getMaximumNumOfRatingsByAnyRater();
        assertEquals( 3, maximumNumberOfRatings);
    }


    @Test
    public void testNumOfRatingsForMovie()
    {
        String movieId = "1798709";
        int numberOfRatings = firstRatingsShort.getNumOfRatingsForMovie(movieId);
        assertEquals( 4, numberOfRatings);
    }

}