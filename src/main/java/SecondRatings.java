import models.Movie;
import models.Rater;

import java.io.IOException;
import java.util.ArrayList;

public class SecondRatings
{
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings(String movieFile, String ratingsFile) throws IOException
    {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies( movieFile );
        myRaters = firstRatings.loadRaters( ratingsFile );
    }

    /**
     *
     * @return the number of movies that were read in and stored in the ArrayList of type Movie.
     */
    public int getMovieSize()
    {
        return myMovies.size();
    }


    /**
     *
      * @return the number of raters that were read in and stored in the ArrayList of type Rater.
     */
    public int getRaterSize()
    {
        return myRaters.size();
    }
}
