import models.IRater;
import models.Movie;
import models.Rater;
import models.Rating;

import java.io.IOException;
import java.util.ArrayList;

public class SecondRatings
{
    private ArrayList<Movie> myMovies;
    private ArrayList<IRater> myRaters;
    private ArrayList<Rating> movieRatings;

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

    public ArrayList<Rating> getAverageRatings( int minimalRaters )
    {
        movieRatings = new ArrayList<>();
        for(Movie movie: myMovies){
            String movieID = movie.getID();
            double rating = getAverageByID( movieID, minimalRaters );
            if(rating != 0.0){
                movieRatings.add(new Rating( movieID, rating ) );
            }
        }
        return  movieRatings;
    }

    public String getTitle( String movieId )
    {
        for(Movie movie: myMovies){
            if(movie.getID().equals( movieId )){
                return movie.getTitle();
            }
        }
        return null;
    }

    /**
     *
     * @param movieTitle is the title of movie to seach for
     * @return the a String Id of the movie if found otherwise return "NO SUCH TITLE"
     */
    public String getMovieIdByTitle( String movieTitle )
    {
        for(Movie movie: myMovies){
            if(movie.getTitle().equals( movieTitle )){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";

    }
}
