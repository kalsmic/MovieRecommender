/*
  The class Rater keeps track of one rater and all their ratings.
 */
package models;

import java.util.ArrayList;

public class Rater
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
     *
     * @param item is the reference to the movie
     * @param rating is a double representing the movie rating
     */
    public void addRating( String item, double rating )
    {
        myRatings.add( new Rating( item, rating ) );
    }

    /**
     *
     * @param item the movie reference
     * @return true is movie has a rating otherwise returns false
     */
    public boolean hasRating( String item )
    {
        for ( Rating myRating : myRatings )
        {
            if ( myRating.getItem().equals( item ) )
            {
                return true;
            }
        }

        return false;
    }

    public String getID()
    {
        return myID;
    }

    /**
     *
     * @param item is the movie item
     * @return the double rating of this item if it is in myRatings. Otherwise this method returns -1
     */
    public double getRating( String item )
    {
        for ( Rating myRating : myRatings )
        {
            if ( myRating.getItem().equals( item ) )
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
    public int numRatings()
    {
        return myRatings.size();
    }

    /**
     *
     * @return an ArrayList of Strings representing a list of all the items that have been rated.
     */
    public ArrayList<String> getItemsRated()
    {
        ArrayList<String> list = new ArrayList<>();
        for ( Rating myRating : myRatings )
        {
            list.add( myRating.getItem() );
        }

        return list;
    }
}
