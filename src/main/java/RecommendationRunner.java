import models.Movie;
import models.MovieDatabase;
import models.RaterDatabase;
import models.Rating;
import models.filters.AllFilters;
import models.filters.GenreFilter;
import models.filters.IFilter;
import models.filters.YearAfterFilter;

import java.io.IOException;
import java.util.ArrayList;

public class RecommendationRunner implements Recommender
{

    public RecommendationRunner() throws IOException
    {
        MovieDatabase.initialize( "data/ratedmoviesfull.csv" );
        RaterDatabase.initialize( "ratings.csv" );

    }

    public static void main( String[] args ) throws IOException
    {
        RecommendationRunner rr = new RecommendationRunner();
        rr.getItemsToRate();
        rr.printRecommendationsFor( "3" );
    }

    @Override
    public ArrayList<String> getItemsToRate()
    {
        IFilter genreFilter = new GenreFilter( "Action" );
        IFilter yearAfter = new YearAfterFilter( 2014 );
        AllFilters filter = new AllFilters();
        filter.addFilters( genreFilter );
        filter.addFilters( yearAfter );
        return new ArrayList<>( MovieDatabase.filterBy( filter ).subList( 0, 10 ) );
    }

    @Override
    public void printRecommendationsFor( String webRaterID )
    {
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings( webRaterID, 10, 3 );
        System.out.println(
                "<style>\n" + "table {\n" + "  margin-left: auto;\n" + "  margin-right: auto;\n" + "    border-collapse: collapse;\n" + "  border: 1px solid gray;\n" + "}\n" + "  \n" + "th,td {\n" + "  border: 1px solid gray;\n" + "  padding: 10px;\n" + "}\n" + ".text {\n" + "  padding: 0 20px 20px;\n" + "}\n" + ".text > button {\n" + "  background: lightblue;\n" + "  border: 0;\n" + "  color: white;\n" + "  padding: 10px;\n" + "  width: 100%;\n" + "  }\n" + "</style>" );
        if ( ratings.isEmpty() )
        {
            System.out.println( "<p><b> Sorry no movie recommendations for you at the moment! </b></p>" );
        }
        System.out.println( "<table>" );
        System.out.println( "<thead>" );
        System.out.println( "<tr>" );
        System.out.println( "<th colspan=\"3 \">" );

        System.out.println( "<h1 class=\"title\">Top 10 movie recomendations</h1>" );
        System.out.println( "</th>" );
        System.out.println( "</tr>" );


        System.out.println( "<tr>" );
        System.out.println( "<th>Rank</th>" );
        System.out.println( "<th>Poster</th>" );
        System.out.println( "<th>Details</th>" );
        System.out.println( "<tr>" );
        System.out.println( "</thead>" );
        System.out.println( "<tbody>" );

        System.out.println( " <div class=\"grid\">" );
        for ( int i = 0; i <= 9 && i < ratings.size(); i++ )
        {
            Rating rating = ratings.get( i );
            Movie movie = MovieDatabase.getMovie( rating.getItemId() );
            String title = movie.getTitle();
            String poster = movie.getPoster();
            String[] genres = movie.getGenres().split( "," );
            ArrayList<String> directors = movie.getDirectors();
            int duration = movie.getMinutes();
            int year = movie.getYear();


            System.out.println( "<tr>" );
            System.out.println( "<td> #" + ( i + 1 ) + "</td>" );
            System.out.println( "<td><img src=\"" + poster + "\" alt=\"" + title + " poster image\"/></td>" );
            System.out.println( "<td>" );
            System.out.println( "<div class=\"text\">" );

            System.out.println( "<p><b>Title    : </b>" + title + "</p>" );
            System.out.println( "<p><b>Rank     : #</b>" + ( i + 1 ) + "</p>" );
            System.out.println( "<p><b>Duration : </b>" + duration + " minutes</p>" );
            System.out.print( "<p><b>Genres   : </b>" );

            for ( String genre : genres )
            {
                System.out.print( "<button>" + genre + "</button>" );
            }
            System.out.println( "</p>" );

            System.out.print( "<p><b>Directors  : </b>" );

            for ( String director : directors )
            {
                System.out.print( "<button>" + director + "</button>" );
            }
            System.out.println( "</p>" );

            System.out.println( "  <p><b>Year: </b> " + year + "</p>" );
            if ( RaterDatabase.getRater( webRaterID ).hasRating( movie.getID() ) )
            {
                System.out.println( "<button> Watched <input type=\"checkbox\" checked disabled></button>" );
            }
            System.out.println( "</div>" );

            System.out.println( "</td>" );


            System.out.println( "</tr>" );
        }
        System.out.println( "</tbody>" );

        System.out.println( "</table>" );

    }

}