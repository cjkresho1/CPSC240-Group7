import java.util.Comparator;

/**
 * Implements a comparator by BikePart number for the BikePart class
 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work.
 * @author Libbie
 *
 */
public class IntegerComparator implements Comparator<BikePart>
{

    @Override
    public int compare(BikePart o1, BikePart o2)
    {
        return (o1.getNumber() - o2.getNumber());

    }

}