package models.filters;

import models.Movie;
import models.MovieDatabase;

public class YearAfterFilter implements IFilter
{
    private final int year;

    public YearAfterFilter( int year )
    {
        this.year = year;
    }


    /**
     * @param movieId represents the movie Id
     * @return true if the movie came out in a specified year or later. Otherwise this method returns false.
     */
    @Override
    public boolean satisfies( String movieId )
    {
        Movie movie = MovieDatabase.getMovie( movieId );
        if ( movie == null )
        {
            return false;
        }
        return movie.getYear() >= year;
    }
}
