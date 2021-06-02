import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testGetMovieSize(){
        assertEquals( 5, secondRatingsShort.getMovieSize() );
        assertEquals( 3143,  secondRatingsFull.getMovieSize());
    }

    @Test
    public void testGetRaterSize(){
        assertEquals( 5, secondRatingsShort.getRaterSize() );
        assertEquals( 1048,  secondRatingsFull.getRaterSize());
    }

}