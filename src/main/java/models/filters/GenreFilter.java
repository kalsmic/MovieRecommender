package models.filters;

import models.Movie;
import models.MovieDatabase;

public class GenreFilter implements IFilter
{
    private final String genre;

    public GenreFilter( String genre )
    {
        this.genre = genre;
    }

    /**
     * @param movieId represents the movie Id
     * @return true if the movie has specified genre. Otherwise this method returns false.
     */
    @Override
    public boolean satisfies( String movieId )
    {
        Movie movie = MovieDatabase.getMovie( movieId );
        if ( movie == null )
        {
            return false;
        }
        return movie.getGenres().contains( genre.toLowerCase() );
    }
}
