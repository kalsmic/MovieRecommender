import models.IRater;
import models.MovieDatabase;
import models.Rating;
import models.filters.IFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ThirdRatings
{
    private final ArrayList<IRater> myRaters;


    public ThirdRatings( String ratingsFile ) throws IOException
    {
        FirstRatings firstRatings = new FirstRatings();
        this.myRaters = firstRatings.loadRaters( ratingsFile );
    }

    /**
     * @return the number of raters that were read in and stored in the ArrayList of type Rater.
     */
    public int getRaterSize()
    {
        return myRaters.size();
    }

    /**
     * @param movieId       is String id of the movie
     * @param minimalRaters minimum number of raters
     * @return he average movie rating for this ID if there are at least minimalRaters ratings. If there are not
     * minimalRaters ratings, then it returns 0.0.
     */
    private double getAverageByID( String movieId, int minimalRaters )
    {
        double rating = 0.0;
        int numRaters = 0;
        int totalRating = 0;
        for ( IRater rater : myRaters )
        {
            if ( rater.getItemsRated().contains( movieId ) )
            {
                totalRating += rater.getRating( movieId );
                numRaters += 1;
            }
        }
        if ( numRaters >= minimalRaters )
        {
            rating = (double) totalRating / numRaters;
        }
        return rating;
    }

    public ArrayList<Rating> getAverageRatings( IFilter filter, int minimalRaters )
    {
        ArrayList<Rating> movieRatings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy( filter );
        for ( String movieID : movies )
        {
            double rating = getAverageByID( movieID, minimalRaters );
            if ( rating != 0.0 )
            {
                movieRatings.add( new Rating( movieID, rating ) );
            }
        }
        Collections.sort( movieRatings );
        return movieRatings;
    }


}
