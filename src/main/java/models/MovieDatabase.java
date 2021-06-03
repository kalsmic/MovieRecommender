package models;

import helpers.FileHelper;
import models.filters.IFilter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MovieDatabase
{
    // key if movieId String, value is Movie Object
    public static HashMap<String, Movie> movies;

    public static void initialize( String moviefile ) throws IOException
    {
        MovieDatabase.loadMovies( moviefile );

    }

    /**
     * This method should process every record from the CSV file whose name is {@fileName}, a file of movies
     *
     * @param fileName the CSV file containing the movies
     */
    private static void loadMovies( String fileName ) throws IOException
    {
        MovieDatabase.movies = new HashMap<>();

        CSVParser csvParser = (CSVParser) new FileHelper( fileName ).getCSVParser();

        for ( CSVRecord csvRecord : csvParser )
        {
            String movieId = csvRecord.get( "id" );
            String title = csvRecord.get( "title" );
            String year = csvRecord.get( "year" );
            String genres = csvRecord.get( "genre" );
            String director = csvRecord.get( "director" );
            String country = csvRecord.get( "country" );
            String poster = csvRecord.get( "poster" );
            int minutes = Integer.parseInt( csvRecord.get( "minutes" ) );
            Movie movie = new Movie( movieId, title, year, genres, director, country, poster, minutes );
            movies.put( movieId, movie );
        }
    }

    /**
     * @return the number of movies in the database.
     */
    public static int getNumMovies()
    {
        return movies.size();
    }

    /**
     * @param filter is an IFilter parameter
     * @return an ArrayList of type String of movie IDs that match the filtering criteria.
     */
    public static ArrayList<String> filterBy( IFilter filter )
    {
        ArrayList<String> movieIds = new ArrayList<>();
        for ( String movieId : movies.keySet() )
        {
            if ( filter.satisfies( movieId ) )
            {
                movieIds.add( movieId );
            }
        }
        return movieIds;
    }

    /**
     * @param movieId is the Id of the movie
     * @return the year in which movie was published
     */
    public static int getYear( String movieId )
    {
        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return -1;
        }
        return MovieDatabase.getMovie( movieId ).getYear();
    }

    /**
     * @param movieId is the Id of the movie
     * @return movie title if movieId exists otherwise return MOVIE DOES NO EXIST
     */
    public static String getTitle( String movieId )
    {
        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return "MOVIE DOES NO EXIST";
        }
        return MovieDatabase.movies.get( movieId ).getTitle();
    }

    /**
     * @param movieId is the Id of the movie
     * @return movie title if movieId exists otherwise return MOVIE DOES NO EXIST
     */
    public static Movie getMovie( String movieId )
    {
        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return null;
        }
        return MovieDatabase.movies.get( movieId );
    }

    /**
     * @param movieId is the Id of the movie
     * @return the poster url if exists otherwise return N/A
     */
    public static String getPoster( String movieId )
    {
        String poster = "N/A";
        if ( MovieDatabase.movies.containsKey( movieId ) )
        {
            poster = MovieDatabase.movies.get( movieId ).getPoster();
        }
        return poster;
    }

    /**
     * @param movieId is the Id of the movie
     * @return the length of movie if exists otherwise return 0;
     */
    public static int getMinutes( String movieId )
    {
        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return 0;
        }
        return MovieDatabase.movies.get( movieId ).getMinutes();
    }

    /**
     * @param movieId is the Id of the movie
     * @return one String of one or more countries the film was made in, separated by commas
     */
    public static String getCountry( String movieId )
    {
        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return null;
        }
        return MovieDatabase.movies.get( movieId ).getCountry();
    }


    /**
     * @param movieId is the Id of the movie
     * @return one String of one or more genres separated by commas
     */
    public static String getGenres( String movieId )
    {
        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return null;
        }
        return MovieDatabase.movies.get( movieId ).getGenres();
    }

    /**
     * @param movieId is the Id of the movie
     * @return an ArrayList of Strings of one or more directors of the movie.
     */
    public static ArrayList<String> getDirector( String movieId )
    {

        if ( !MovieDatabase.movies.containsKey( movieId ) )
        {
            return new ArrayList<>();
        }
        return MovieDatabase.movies.get( movieId ).getDirectors();
    }


}
