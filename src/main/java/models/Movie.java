/*
  The class Movie is a Plain Old Java Object (POJO) class for storing the data about one movie
 */
package models;

import java.util.ArrayList;

// An immutable passive data object (PDO) to represent item data
public class Movie
{
    private final String id;
    private final String title;
    private final int year;
    ArrayList<String> directors;
    private ArrayList<String> genres;

    private String country;
    private String poster;
    private int minutes;

    /**
     * @param anID      is a String variable representing the IMDB ID of the movie
     * @param aTitle    is a String variable for the movie’s title
     * @param aYear     is an integer representing the year
     * @param theGenres is one String of one or more genres separated by commas
     */
    public Movie( String anID, String aTitle, String aYear, String theGenres )
    {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt( aYear.trim() );
        setGenres( theGenres );

    }

    /**
     * @param anID         is a String variable representing the IMDB ID of the movie
     * @param aTitle       is a String variable for the movie’s title
     * @param aYear        is an integer representing the year
     * @param theGenres    is one String of one or more genres separated by commas
     * @param theDirectors is one String of one or more directors of the movie separated by commas
     * @param aCountry     is one String of one or more countries the film was made in, separated by commas
     * @param aPoster      is a String that is a link to an image of the movie poster if one exists, or “N/A” if no
     *                     poster exists
     * @param theMinutes   is an integer for the length of the movie
     */
    public Movie( String anID, String aTitle, String aYear, String theGenres, String theDirectors, String aCountry, String aPoster, int theMinutes )
    {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt( aYear.trim() );
        setGenres( theGenres );
        directors = new ArrayList<>();
        setDirectors( theDirectors );
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    /**
     * @return one String of one or more genres separated by commas
     */
    public String getGenres()
    {

        return genres.toString().replace( "[","" ).replace( "]","" );
    }

    private void setGenres( String genres )
    {
        this.genres = new ArrayList<>();

        for ( String genre : genres.split( "," ) )
        {
            this.genres.add( genre.toLowerCase() );
        }
    }

    /**
     * @return a String variable representing the IMDB ID of the movie
     */
    public String getID()
    {
        return id;
    }

    /**
     * @return a String representing the title of this movie
     */
    public String getTitle()
    {
        return title;
    }

    public ArrayList<String> getDirectors()
    {
        return directors;
    }

    /**
     * @return an integer representing the year in which this movie was published
     */
    public int getYear()
    {
        return year;
    }



    /**
     * @return one String of one or more countries this film was made in, separated by commas
     */
    public String getCountry()
    {
        return country;
    }

    private void setDirectors( String directors )
    {
        for ( String director : directors.split( "," ) )
        {
            this.directors.add( director.trim() );
        }
    }

    /**
     * @return a String that is a link to an image of the movie poster if one exists, or “N/A” if no poster exists
     */
    public String getPoster()
    {
        return poster;
    }

    /**
     * @return an integer for the length of this movie
     */
    public int getMinutes()
    {
        return minutes;
    }


    /**
     * @return a string of the item's information
     */
    public String toString()
    {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }

}
