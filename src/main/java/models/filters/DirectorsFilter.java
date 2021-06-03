package models.filters;

import models.Movie;
import models.MovieDatabase;

public class DirectorsFilter implements IFilter
{
    private final String directors;

    /**
     * @param directors is a command separated String of directors
     */
    public DirectorsFilter( String directors )
    {
        this.directors = directors;
    }


    /**
     * @param movieId represents the movie Id
     * @return true if the movie satisfies the criteria of all the filters in the filters ArrayList. Otherwise this
     * method returns false.
     */
    @Override
    public boolean satisfies( String movieId )
    {
        Movie movie = MovieDatabase.getMovie( movieId );
        if ( movie == null )
        {
            return false;
        }
        for ( String director : directors.split( "," ) )
        {
            if ( movie.getDirectors().contains( director ) )
            {
                return true;
            }
        }
        return false;

    }
}
