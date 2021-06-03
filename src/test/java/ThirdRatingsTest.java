import models.MovieDatabase;
import models.Rating;
import models.filters.IFilter;
import models.filters.TrueFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class ThirdRatingsTest
{

    ThirdRatings thirdRatings;

    @BeforeAll
    public void setUp() throws IOException
    {
        thirdRatings = new ThirdRatings( "data/ratings_short.csv" );
        MovieDatabase.initialize( "data/ratedmovies_short.csv" );
    }

    @AfterAll
    public void tearDown()
    {
        thirdRatings = null;
    }

    @Test
    public void getRaterSize()
    {
        assertEquals( 5, thirdRatings.getRaterSize() );
    }

    @Test
    public void getAverageRatings()
    {
        IFilter filter = new TrueFilter();
        assertEquals( 0, thirdRatings.getAverageRatings( filter, 5 ).size() );
        ArrayList<Rating> ratings = thirdRatings.getAverageRatings( filter, 4 );
        assertEquals( 2, ratings.size() );
        assertEquals( 8.25, ratings.get( 0 ).getValue() );
        assertEquals( 9.0, ratings.get( 1 ).getValue() );

    }
}