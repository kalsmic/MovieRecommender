package models;

import java.util.ArrayList;
import java.util.HashMap;

public interface IRater
{
    /**
     * @param movieId is the reference to the movie
     * @param rating  is a double representing the movie rating
     */
    void addRating( String movieId, double rating );

    /**
     * @param movieId the movie reference
     * @return true is movie has a rating otherwise returns false
     */
    boolean hasRating( String movieId );

    /**
     * @return a String id of Rater
     */
    String getID();

    /**
     * @param movieId is the movie Id reference
     * @return the double rating of this movieId if it is in myRatings. Otherwise this method returns -1
     */
    double getRating( String movieId );

    /**
     * @return the number of ratings this rater has.
     */
    int numRatings();

    /**
     * @return an ArrayList of Strings representing a list of all the items that have been rated.
     */
    ArrayList<String> getItemsRated();
}
