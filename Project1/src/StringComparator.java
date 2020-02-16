import java.util.Comparator;

/**
 * Implements a comparator by BikePart name for the BikePart class
 * @author Libbie
 *
 */
public class StringComparator implements Comparator<BikePart>
{

    @Override
    public int compare(BikePart o1, BikePart o2)
    {
        return o1.getName().compareToIgnoreCase(o2.getName());

    }

}
