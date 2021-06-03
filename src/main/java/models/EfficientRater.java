package models;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements IRater
{
    //The key in the HashMap is a movie ID, and its value is a rating associated with this movie
    private HashMap<String, Rating> myRatings;
    private final String myID;

    public EfficientRater( String id )
    {
        this.myID = id;
        myRatings = new HashMap<>();
    }


    @Override
    public void addRating( String movieId, double rating )
    {
        myRatings.put( movieId, new Rating( movieId,rating ));
    }

    @Override
    public boolean hasRating( String movieId )
    {
        return myRatings.containsKey( movieId );
    }

    @Override
    public String getID()
    {
        return this.myID;
    }

    @Override
    public double getRating( String movieId )
    {
        if(myRatings.containsKey( movieId )){
            return myRatings.get( movieId ).getValue();
        }
        return 0;
    }

    @Override
    public int numRatings()
    {
        return myRatings.size();
    }

    @Override
    public ArrayList<String> getItemsRated()
    {
        return new ArrayList<String>(myRatings.keySet());
    }
}
