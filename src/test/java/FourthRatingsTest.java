import models.MovieDatabase;
import models.RaterDatabase;
import models.Rating;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class FourthRatingsTest
{

    FourthRatings fourthRatings;

    @BeforeAll
    public void setUp() throws IOException
    {

        MovieDatabase.initialize( "data/ratedmoviesfull.csv" );
        RaterDatabase.initialize( "ratings.csv" );
        fourthRatings = new FourthRatings();


    }

    @AfterAll
    public void tearDown()
    {
        fourthRatings = null;
    }

    @Test
    public void getRaterSize()
    {
        assertEquals( 1048, fourthRatings.getRaterSize() );
    }

    @Test
    public void getAverageRatings()
    {
        IFilter filter = new TrueFilter();
        assertEquals( 463, fourthRatings.getAverageRatingsByFilter( filter, 5 ).size() );
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter( filter, 4 );
        assertEquals( 579, ratings.size() );
        assertEquals( 2.8, ratings.get( 0 ).getValue() );
        assertEquals( 3.125, ratings.get( 1 ).getValue() );

    }

    @Test
    public void testAverageRatings()
    {
        assertEquals( 29, fourthRatings.getAverageRatingsByFilter( new TrueFilter(), 35 ).size() );
    }

    @Test
    public void testAverageRatingsByYearAfter()
    {
        IFilter filter = new YearAfterFilter( 2000 );
        assertEquals( 88, fourthRatings.getAverageRatingsByFilter( filter, 20 ).size() );
    }

    @Test
    public void testAverageRatingsByGenre()
    {
        IFilter filter = new GenreFilter( "Comedy" );
        assertEquals( 19, fourthRatings.getAverageRatingsByFilter( filter, 20 ).size() );
    }

    @Test
    public void testAverageRatingsByMinutes()
    {
        IFilter filter = new MinutesFilter( 105, 135 );
        assertEquals( 231, fourthRatings.getAverageRatingsByFilter( filter, 5 ).size() );
    }

    @Test
    public void testAverageRatingsByDirector()
    {
        IFilter filter = new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack" );
        assertEquals( 22, fourthRatings.getAverageRatingsByFilter( filter, 4 ).size() );
    }

    @Test
    public void testAverageRatingsByYearAfterAndGenre()
    {
        IFilter yearAfter = new YearAfterFilter( 1990 );
        IFilter genre = new GenreFilter( "Drama" );
        AllFilters filter = new AllFilters();
        filter.addFilters( yearAfter );
        filter.addFilters( genre );
        assertEquals( 132, fourthRatings.getAverageRatingsByFilter( filter, 8 ).size() );
    }

    @Test
    public void testAverageRatingsByDirectorsAndMinutes()
    {
        IFilter minutes = new MinutesFilter( 90, 180 );
        IFilter directors = new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack" );
        AllFilters filter = new AllFilters();
        filter.addFilters( minutes );
        filter.addFilters( directors );

        assertEquals( 15, fourthRatings.getAverageRatingsByFilter( filter, 3 ).size() );
    }

    @ParameterizedTest( name = "{index}=>raterId={0}, minimalRaters={1},numSimilarRaters={2},title={3}" )
    @CsvSource( {"65,5,20,The Fault in Our Stars", "337,3,10,Frozen", "71,5,20,About Time"} )
    public void testSimilarRatings( String raterId, int minimalRaters, int numSimilarRaters, String title )
    {
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings( raterId, numSimilarRaters, minimalRaters );
        assertEquals( title, MovieDatabase.getTitle( ratings.get( 0 ).getItemId() ) );
    }

    @ParameterizedTest( name = "{index}=>raterId={0}, minimalRaters={1},numSimilarRaters={2},genre={3},title={4}" )
    @CsvSource( {"65,5,20,Action,Rush", "964,5,20,Mystery,Gone Girl"} )
    public void testSimilarRatingsByGenre( String raterId, int minimalRaters, int numSimilarRaters, String genre, String title )
    {
        IFilter filter = new GenreFilter( genre );

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRaters,
                filter );
        assertEquals( title, MovieDatabase.getTitle( ratings.get( 0 ).getItemId() ) );
    }

    @ParameterizedTest( name = "{index}=>raterId={0}, minimalRaters={1},numSimilarRaters={2},directors={3},title={4}" )
    @CsvSource( value = {"1034:3:10:Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone:Unforgiven", "120:2:10:Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh:Star Trek",},
                delimiter = ':' )
    public void testSimilarRatingsByDirector( String raterId, int minimalRaters, int numSimilarRaters, String directors, String title )
    {
        IFilter filter = new DirectorsFilter( directors );

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRaters,
                filter );
        assertEquals( title, MovieDatabase.getTitle( ratings.get( 0 ).getItemId() ) );
    }

    @ParameterizedTest( name = "{index}=>raterId={0}, minimalRaters={1},numSimilarRaters={2},genre={3},minMinutes={4},maxMinutes={5},title={6}" )
    @CsvSource( {"65,5,10,Adventure,100,200,Interstellar", "168,3,10,Drama,80,160,The Imitation Game"} )
    public void testSimilarRatingsByGenreAndMinutes( String raterId, int minimalRaters, int numSimilarRaters, String genre, int minMinutes, int maxMinutes, String title )
    {
        IFilter genreFilter = new GenreFilter( genre );
        IFilter minutesFilter = new MinutesFilter( minMinutes, maxMinutes );

        AllFilters filter = new AllFilters();
        filter.addFilters( genreFilter );
        filter.addFilters( minutesFilter );

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRaters,
                filter );
        assertEquals( title, MovieDatabase.getTitle( ratings.get( 0 ).getItemId() ) );
    }

    @ParameterizedTest( name = "{index}=>raterId={0}, minimalRaters={1},numSimilarRaters={2},genre={3},minMinutes={4},maxMinutes={5},title={6}" )
    @CsvSource( {"65,5,10,2000,80,100,The Grand Budapest Hotel", "314,5,10,1975,70,200,Nightcrawler"} )
    public void testSimilarRatingsByYearAfterAndMinutes( String raterId, int minimalRaters, int numSimilarRaters, int year, int minMinutes, int maxMinutes, String title )
    {
        IFilter yearAfterFilter = new YearAfterFilter( year );
        IFilter minutesFilter = new MinutesFilter( minMinutes, maxMinutes );

        AllFilters filter = new AllFilters();
        filter.addFilters( yearAfterFilter );
        filter.addFilters( minutesFilter );

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRaters,
                filter );
        assertEquals( title, MovieDatabase.getTitle( ratings.get( 0 ).getItemId() ) );
    }
}