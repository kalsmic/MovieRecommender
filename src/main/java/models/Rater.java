/*
  The class Rater keeps track of one rater and all their ratings.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Rater implements IRater
{
    private final String myID;
    private final ArrayList<Rating> myRatings;

    /**
     *
     * @param id is the ID of the rater
     */
    public Rater( String id )
    {
        myID = id;
        myRatings = new ArrayList<>();
    }

    /**
     * @param movieId is the reference to the movie
     * @param rating  is a double representing the movie rating
     */
    @Override
    public void addRating( String movieId, double rating )
    {
        myRatings.add( new Rating( movieId, rating ) );
    }

    /**
     *
     * @param movieId the movie reference
     * @return true is movie has a rating otherwise returns false
     */
    @Override
    public boolean hasRating( String movieId )
    {
        for ( Rating myRating : myRatings )
        {
            if ( myRating.getMovieId().equals( movieId ) )
            {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @return a String ID of the rater
     */
    @Override
    public String getID()
    {
        return myID;
    }

    /**
     *
     * @param movieId is the movie Id reference
     * @return the double rating of this movieId if it is in myRatings. Otherwise this method returns -1
     */
    @Override
    public double getRating( String movieId )
    {
        for ( Rating myRating : myRatings )
        {
            if ( myRating.getMovieId().equals( movieId ) )
            {
                return myRating.getValue();
            }
        }

        return -1;
    }

    /**
     *
     * @return the number of ratings this rater has.
     */
    @Override
    public int numRatings()
    {
        return myRatings.size();
    }

    /**
     *
     * @return an ArrayList of Strings representing a list of all the items that have been rated.
     */
    @Override
    public ArrayList<String> getItemsRated()
    {
        ArrayList<String> list = new ArrayList<>();
        for ( Rating myRating : myRatings )
        {
            list.add( myRating.getMovieId() );
        }

        return list;
    }
}
