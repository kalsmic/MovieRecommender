package models.filters;

import models.Movie;
import models.MovieDatabase;

public class MinutesFilter implements IFilter
{
    private final int maxMinutes;
    private final int minMinutes;

    /**
     * @param minMinutes is the minimum number of minutes for a movie's runtime
     * @param maxMinutes is the maximum number of minutes for a movie's runtime
     */
    public MinutesFilter( int minMinutes, int maxMinutes )
    {
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
    }

    /**
     * @param movieId represents the movie Id
     * @return true if movie's running time is at least a minimum number of minutes and no more than a maximum number of
     * minutes.
     */
    @Override
    public boolean satisfies( String movieId )
    {
        Movie movie = MovieDatabase.getMovie( movieId );
        if ( movie == null )
        {
            return false;
        }
        int minutes = movie.getMinutes();
        return minutes <= maxMinutes && minutes >= minMinutes;
    }
}
