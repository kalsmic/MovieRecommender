import models.MovieDatabase;
import models.filters.AllFilters;
import models.filters.DirectorsFilter;
import models.filters.GenreFilter;
import models.filters.IFilter;
import models.filters.MinutesFilter;
import models.filters.TrueFilter;
import models.filters.YearAfterFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class FiltersTest
{
    ThirdRatings thirdRatings;

    @BeforeAll
    public void setUp() throws IOException
    {
        MovieDatabase.initialize( "data/ratedmoviesfull.csv" );
        thirdRatings = new ThirdRatings( "data/ratings.csv" );

    }

    @AfterAll
    public void tearDown()
    {
        thirdRatings = null;
    }

    @Test
    public void testAverageRatings() throws IOException
    {
        assertEquals( 29, thirdRatings.getAverageRatings( new TrueFilter(), 35 ).size() );
    }

    @Test
    public void testAverageRatingsByYearAfter() throws IOException
    {
        IFilter filter = new YearAfterFilter( 2000 );
        assertEquals( 88, thirdRatings.getAverageRatings( filter, 20 ).size() );
    }

    @Test
    public void testAverageRatingsByGenre() throws IOException
    {
        IFilter filter = new GenreFilter( "Comedy" );
        assertEquals( 19, thirdRatings.getAverageRatings( filter, 20 ).size() );
    }

    @Test
    public void testAverageRatingsByMinutes() throws IOException
    {
        IFilter filter = new MinutesFilter( 105, 135 );
        assertEquals( 231, thirdRatings.getAverageRatings( filter, 5 ).size() );
    }

    @Test
    public void testAverageRatingsByDirector() throws IOException
    {
        IFilter filter = new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack" );
        assertEquals( 22, thirdRatings.getAverageRatings( filter, 4 ).size() );
    }

    @Test
    public void testAverageRatingsByYearAfterAndGenre() throws IOException
    {
        IFilter yearAfter = new YearAfterFilter( 1990 );
        IFilter genre = new GenreFilter( "Drama" );
        AllFilters filter = new AllFilters();
        filter.addFilters( yearAfter );
        filter.addFilters( genre );
        assertEquals( 132, thirdRatings.getAverageRatings( filter, 8 ).size() );
    }

    @Test
    public void testAverageRatingsByDirectorsAndMinutes() throws IOException
    {
        IFilter minutes = new MinutesFilter( 90, 180 );
        IFilter directors = new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack" );
        AllFilters filter = new AllFilters();
        filter.addFilters( minutes );
        filter.addFilters( directors );

        assertEquals( 15, thirdRatings.getAverageRatings( filter, 3 ).size() );
    }


}
