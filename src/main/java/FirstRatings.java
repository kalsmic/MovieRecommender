import models.Movie;
import models.Rater;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FirstRatings
{
    ArrayList<Movie> movies;
    ArrayList<Rater> raters;
    HashMap<String, HashSet<Movie>> allDirectors;


    public FirstRatings()
    {
        movies = new ArrayList<>();
        raters = new ArrayList<>();
        allDirectors = new HashMap<>()
        {
        };

    }

    public ArrayList<Movie> loadMovies( String fileName ) throws IOException
    {

        CSVParser csvParser = (CSVParser) new FileHelper( fileName ).getCSVParser();

        for ( CSVRecord csvRecord : csvParser )
        {
            String id = csvRecord.get( "id" );
            String title = csvRecord.get( "title" );
            String year = csvRecord.get( "year" );
            String genres = csvRecord.get( "genre" );
            String director = csvRecord.get( "director" );
            String country = csvRecord.get( "country" );
            String poster = csvRecord.get( "poster" );
            int minutes = Integer.parseInt( csvRecord.get( "minutes" ) );
            Movie movie = new Movie( id, title, year, genres, director, country, poster, minutes );
            addToAllDirectors( movie );
            movies.add( movie );
        }
        return movies;
    }

    /**
     * @return one String of one or more directors of the movie separated by commas
     */
    public ArrayList<String> getAllDirectors()
    {
        return new ArrayList<>( allDirectors.keySet() );
    }

    private void addToAllDirectors( Movie movie )
    {

        for ( String director : movie.getDirectors() )
        {
            // check if director exists
            if ( allDirectors.containsKey( director ) )
            {
                // add movie to directors movies
                allDirectors.get( director ).add( movie );
            }
            else
            {
                // add director and movie
                HashSet<Movie> newMovie = new HashSet<>();
                newMovie.add( movie );
                allDirectors.put( director, newMovie );
            }
        }

    }

    /**
     * @return the maximum number of movies by any director, and who the directors are that directed that many movies
     */
    public int maximumNumberOfMoviesByAnyDirector()
    {
        int maximumNumMovies = 0;

        HashMap<String, HashSet<Movie>> directors = allDirectors;
        String directorName =null;
        // look at every director

        for ( String director : directors.keySet() )
        {
            // Get the number of movies they have directed
            int currNumOfMovies = directors.get( director ).size();

            // if their number exceeds the maximumNumMovies
            if ( currNumOfMovies > maximumNumMovies )
            {
                // set the maximumNumMovies to theirs
                maximumNumMovies = currNumOfMovies;
                directorName = director;
            }
        }

        System.out.println(directorName + " directed more films than any other director");
        return maximumNumMovies;
    }

    /**
     * @param genre the genre to look for
     * @return the number of movies which include the specified genre.
     */
    public int getNumMoviesForGenre( String genre )
    {
        int numOfMovies = 0;
        for ( Movie movie : movies )
        {
            if ( movie.getGenres().contains( genre.toLowerCase() ) )
            {
                numOfMovies += 1;
            }
        }
        return numOfMovies;
    }

    /**
     * This method should process every record from the CSV file whose name is {@fileName}, a file of raters and their
     * ratings, and return an ArrayList of type Rater with all the rater data from the file
     *
     * @param fileName the CSV file containing the ratinds
     * @return an ArrayList of type Rater with all the rater data from the file
     */
    public ArrayList<Rater> loadRaters( String fileName ) throws IOException
    {
        CSVParser csvParser = (CSVParser) new FileHelper( fileName ).getCSVParser();
        for ( CSVRecord csvRecord : csvParser )
        {
            String raterId = csvRecord.get( "rater_id" );
            String movieId = csvRecord.get( "movie_id" );
            double rating = Double.parseDouble( csvRecord.get( "rating" ) );
            Rater rater = null;
            // look through the raters arrayList
            for ( Rater curRater : raters )
            {
                // Check if rater already exists
                if ( curRater.getID().equals( raterId ) )
                {
                    rater = curRater;
                    break;
                }
            }

            // Create new Rater object and  add it to the raters  ArrayList
            if ( rater == null )
            {
                rater = new Rater( raterId );
                raters.add( rater );
            }

            rater.addRating( movieId, rating );

        }

        return raters;

    }

    /**
     * @param raterId the rater id
     * @return the number of ratings for a specified rater with id {raterId}
     */
    public int getNumOfRatingsForRater( String raterId )
    {
        int numberOfRatings = 0;

        for ( Rater rater : raters )
        {
            if ( rater.getID().equals( raterId ) )
            {
                numberOfRatings = rater.numRatings();
                break;
            }
        }
        return numberOfRatings;
    }

    /**
     * @return the maximum number of ratings by any rater
     */
    public int getMaximumNumOfRatingsByAnyRater()
    {
        int maximumNumberOfRatings = 0;
        HashMap<Integer, ArrayList<String>> ratingsNum = new HashMap<>();
        String mostRater = null;
        for ( Rater rater : raters )
        {
            int currRatings = rater.numRatings();
            if ( currRatings > maximumNumberOfRatings )
            {
                maximumNumberOfRatings = currRatings;
                mostRater = rater.getID();
            }
            if ( ratingsNum.containsKey( currRatings ) )
            {
                ratingsNum.get( currRatings ).add( rater.getID() );
            }
            else
            {
                ratingsNum.put( currRatings, new ArrayList<>( List.of( rater.getID() ) ) );

            }
        }
        System.out.println(mostRater + " rated the most number of movies");
        System.out.println( "Raters with the same maximum number of ratings " + ratingsNum.get( maximumNumberOfRatings ) );
        return maximumNumberOfRatings;
    }

    /**
     * @param movieId the id of the movie
     * @return the number of ratings the specified movie has.
     */
    public int getNumOfRatingsForMovie( String movieId )
    {
        int numberOfRatings = 0;
        int numberOfDifferentMoviesRatedByRater = 0;
        HashSet<String> moviesRated = new HashSet<>();

        for ( Rater rater : raters )
        {
            if ( !( rater.getRating( movieId ) == -1 ) )
            {
                numberOfRatings += 1;
                for ( String currMovieId : rater.getItemsRated() )
                {
                    moviesRated.add(currMovieId.trim() );

                    if ( !currMovieId.equals( movieId ) )
                    {
                        numberOfDifferentMoviesRatedByRater += 1;
                    }
                }
            }
        }
        System.out.println(moviesRated.size() +" different movies have been rated by all these raters.");
        System.out.println( numberOfDifferentMoviesRatedByRater + " other movies rated" );
        return numberOfRatings;
    }

    /**
     * @param minMinutes is the minimum movie length
     * @return the number of movies are greater than minMinutes in length
     */
    public int getNumOfMoviesLongerThan( int minMinutes )
    {
        int numberOfMovies = 0;
        for ( Movie movie : movies )
        {
            if ( movie.getMinutes() > minMinutes )
            {
                numberOfMovies += 1;
            }
        }
        return numberOfMovies;
    }
}