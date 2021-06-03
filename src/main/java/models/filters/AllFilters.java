package models.filters;

import java.util.ArrayList;

public class AllFilters implements IFilter
{
    private final ArrayList<IFilter> filters;

    public AllFilters()
    {
        this.filters = new ArrayList<>();
    }

    /**
     * This method allows one to add a Filter to the ArrayList filters.
     *
     * @param filter is a Filter of type IFilter
     */
    public void addFilters( IFilter filter )
    {
        filters.add( filter );
    }

    /**
     * @param movieId represents the movie Id
     * @return true if the movie satisfies the criteria of all the filters in the filters ArrayList. Otherwise this
     * method returns false.
     */
    @Override
    public boolean satisfies( String movieId )
    {

        for ( IFilter filter : filters )
        {
            if ( !filter.satisfies( movieId ) )
            {
                return false;
            }
        }
        return true;
    }


}


