package models;

import helpers.FileHelper;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase
{
    private static HashMap<String, IRater> ourRaters;

    /**
     * This method initializes the HashMap ourRaters if it does not exist.
     */
    private static void initialize()
    {
        // this method is only called from addRatings
        if ( ourRaters == null )
        {
            ourRaters = new HashMap<>();
        }
    }

    /**
     * @param filename is a CSV file containing movie ratings
     * @throws IOException if error is encountered reading the file
     */
    public static void initialize( String filename ) throws IOException
    {
        if ( ourRaters == null )
        {
            ourRaters = new HashMap<>();
            addRatings( "data/" + filename );
        }
    }

    /**
     * This method adds rater ratings to the database from a file
     *
     * @param fileName is a CSV file containing movie ratings
     * @throws IOException if error is encountered reading the file
     */
    public static void addRatings( String fileName ) throws IOException
    {
        initialize();
        CSVParser csvParser = (CSVParser) new FileHelper( fileName ).getCSVParser();

        for ( CSVRecord rec : csvParser )
        {
            String id = rec.get( "rater_id" );
            String item = rec.get( "movie_id" );
            String rating = rec.get( "rating" );
            addRaterRating( id, item, Double.parseDouble( rating ) );
        }
    }


    /**
     * @param raterID represents a rater ID
     * @param movieID represents a movie ID
     * @param rating  is the rating the rater raterID has given to the movie movieID
     */
    public static void addRaterRating( String raterID, String movieID, double rating )
    {
        initialize();
        IRater rater;
        if ( ourRaters.containsKey( raterID ) )
        {
            rater = ourRaters.get( raterID );
        }
        else
        {
            rater = new EfficientRater( raterID );
            ourRaters.put( raterID, rater );
        }
        rater.addRating( movieID, rating );
    }

    /**
     * @param id represents the Id of the rater.
     * @return a Rater that has this ID.
     */
    public static IRater getRater( String id )
    {
        initialize();

        return ourRaters.get( id );
    }

    /**
     * @return an ArrayList of Raters from the database.
     */
    public static ArrayList<IRater> getRaters()
    {
        initialize();

        return new ArrayList<IRater>( ourRaters.values() );
    }

    /**
     * @return the number of raters in the database
     */
    public static int size()
    {
        return ourRaters.size();
    }


}