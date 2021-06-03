/*
  The class Rater keeps track of one rater and all their ratings.
 */
package models;

import java.util.ArrayList;

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
     * @param itemId is the reference to the item
     * @param rating is a double representing the item rating
     */
    @Override
    public void addRating( String itemId, double rating )
    {
        myRatings.add( new Rating( itemId, rating ) );
    }

    /**
     *
     * @param itemId the item reference
     * @return true is item has a rating otherwise returns false
     */
    @Override
    public boolean hasRating( String itemId )
    {
        for ( Rating myRating : myRatings )
        {
            if ( myRating.getItemId().equals( itemId ) )
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
     * @param itemId is the item Id reference
     * @return the double rating of this itemId if it is in myRatings. Otherwise this method returns -1
     */
    @Override
    public double getRating( String itemId )
    {
        for ( Rating myRating : myRatings )
        {
            if ( myRating.getItemId().equals( itemId ) )
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
            list.add( myRating.getItemId() );
        }

        return list;
    }
}
