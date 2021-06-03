import models.IRater;
import models.MovieDatabase;
import models.RaterDatabase;
import models.Rating;
import models.filters.IFilter;
import models.filters.TrueFilter;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings
{

    /**
     * @return the number of raters that were read in and stored in the ArrayList of type Rater.
     */
    public int getRaterSize()
    {
        return RaterDatabase.getRaters().size();
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
        for ( IRater rater : RaterDatabase.getRaters() )
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


    public ArrayList<Rating> getAverageRatingsByFilter( IFilter filter, int minimalRaters )
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

    /**
     * This method first translates a rating from the scale 0 to 10 to the scale -5 to 5
     *
     * @param me represents a Rater named me
     * @param r  represents a Rater named r
     * @return the dot product of the ratings of movies that they both rated
     */
    private double dotProduct( IRater me, IRater r )
    {
        double product = 0.0;
        ArrayList<String> ratedByMe = me.getItemsRated(), ratedByR = r.getItemsRated();
        for ( String movieId : ratedByMe )
        {
            if ( ratedByR.contains( movieId ) )
            {
                product += ( me.getRating( movieId ) - 5 ) * ( r.getRating( movieId ) - 5 );
            }
        }
        return product;

    }

    /**
     * @param id is the id of the Rater we want to compare to others
     * @return an ArrayList of type Rating sorted by ratings from highest to lowest rating with the highest rating first
     * and only including those raters who have a positive similarity rating since those with negative values are not
     * similar in any way
     */
    private ArrayList<Rating> getSimilarities( String id )
    {
        ArrayList<Rating> ratings = new ArrayList<>();
        IRater me = RaterDatabase.getRater( id );
        for ( IRater r : RaterDatabase.getRaters() )
        {
            if ( r == me )
            {
                continue;
            }
            double currDotProduct = dotProduct( me, r );
            if ( !( currDotProduct < 0 ) )
            {
                ratings.add( new Rating( r.getID(), currDotProduct ) );
            }
        }
        ratings.sort( Collections.reverseOrder() );
        return ratings;

    }

    /**
     * @param id               represents a rater ID
     * @param numSimilarRaters represents the minimum number of similar Raters
     * @param minimalRaters    represents the minimum number of Raters
     * @return an ArrayList of type Rating, of movies and their weighted average ratings using only the top
     * numSimilarRaters with positive ratings and including only those movies that have at least minimalRaters ratings
     * from those most similar raters (not just minimalRaters rating  s overall).
     */
    public ArrayList<Rating> getSimilarRatings( String id, int numSimilarRaters, int minimalRaters )
    {
        IFilter filter = new TrueFilter();
        return getSimilarRatingsByFilter( id, numSimilarRaters, minimalRaters, filter );
    }

    /**
     * @param id               represents a rater ID
     * @param numSimilarRaters represents the minimum number of similar Raters
     * @param minimalRaters    represents the minimum number of Raters
     * @param filterCriteria   represents a filter used to select movies for rating
     * @return an ArrayList of type Rating, of movies and their weighted average ratings using only the top
     * numSimilarRaters with positive ratings and including only those movies that have at least minimalRaters ratings
     * from those most similar raters (not just minimalRaters rating  s overall).
     */
    public ArrayList<Rating> getSimilarRatingsByFilter( String id, int numSimilarRaters, int minimalRaters, IFilter filterCriteria )
    {
        ArrayList<Rating> similarRatings = getSimilarities( id );
        ArrayList<Rating> weightedRatings = new ArrayList<>();

        for ( String movieID : MovieDatabase.filterBy( filterCriteria ) )
        {
            int numRaters = 0;
            int sum = 0;

            // look at each similar rating in similarRatings not exceeding numSimilarRaters
            for ( int k = 0; k < numSimilarRaters && k < similarRatings.size(); k++ )
            {
                Rating rating = similarRatings.get( k );
                double weight = rating.getValue();
                IRater curRater = RaterDatabase.getRater( rating.getItemId() );
                // check if the rater ,rated movie with movieID
                if ( curRater.hasRating( movieID ) )
                {
                    numRaters += 1;
                    sum += weight * curRater.getRating( movieID );
                }
            }


            if ( numRaters >= minimalRaters )
            {
                double weightedAverage = (double) sum / numRaters;
                weightedRatings.add( new Rating( movieID, weightedAverage ) );
            }
        }
        weightedRatings.sort( Collections.reverseOrder() );

        return weightedRatings;
    }

}
