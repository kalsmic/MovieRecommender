import models.Rater;
import models.Rating;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class SecondRatingsTest
{
    SecondRatings secondRatingsShort;
    SecondRatings secondRatingsFull;

    @BeforeAll
    public void setUp() throws IOException
    {
        secondRatingsShort = new SecondRatings( "data/ratedmovies_short.csv","data/ratings_short.csv" );
        secondRatingsFull = new SecondRatings( "data/ratedmoviesfull.csv","data/ratings.csv" );
    }

    @AfterAll
    public void tearDown()
    {
        secondRatingsShort = null;
        secondRatingsFull = null;
    }

    @Test
    public void testGetMovieSize()
    {
        assertEquals( 5, secondRatingsShort.getMovieSize() );
        assertEquals( 3143, secondRatingsFull.getMovieSize() );
    }

    @Test
    public void testGetRaterSize()
    {
        assertEquals( 5, secondRatingsShort.getRaterSize() );
        assertEquals( 1048, secondRatingsFull.getRaterSize() );
    }

    @Test
    public void testGetAverageRatings()
    {
        int minimalRaters = 2;
        ArrayList<Rating> raters = secondRatingsShort.getAverageRatings(minimalRaters );
        assertEquals( 2, raters.size() );

    }

    @Test
    public void testGetTitle()
    {
        String movieId = "0006414";
        String movieTitle = secondRatingsShort.getTitle(movieId );
        assertEquals( "Behind the Screen", movieTitle );
    }

    @Test
    public void testGetMovieIdByTitle()
    {
        String movieTitle ="Behind the Screen";
        String movieId = secondRatingsShort.getMovieIdByTitle(movieTitle );
        assertEquals( "0006414", movieId );
    }





}