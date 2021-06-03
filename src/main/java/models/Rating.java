/*
  The class Rating is also a POJO class for storing the data about one rating of an item
 */
package models;

// An immutable passive data object (PDO) to represent the rating data
public class Rating implements Comparable<Rating>
{
    private final String itemId;
    private final double value;

    /**
     * @param itemId is a String description of the item being rated (for this assignment you should use the IMDB ID of
     *               the item being rated)
     * @param aValue is a double of the actual rating
     */
    public Rating( String itemId, double aValue )
    {
        this.itemId = itemId;
        value = aValue;
    }

    /**
     * @return item being rated
     */
    public String getItemId()
    {
        return itemId;
    }

    /**
     * @return the value of this rating (as a number so it can be used in calculations)
     */
    public double getValue()
    {
        return value;
    }

    /**
     * @return Returns a string of all the rating information
     */
    public String toString()
    {
        return "[" + getItemId() + ", " + getValue() + "]";
    }

    /**
     * This method compares this rating with another rating
     *
     * @param other is the other item rating
     * @return -1 if other current item has higher rating, 0 if the items have an equal rating otherwise return 1 if
     * other item has higher rating
     */
    public int compareTo( Rating other )
    {
        return Double.compare( value, other.value );

    }
}
