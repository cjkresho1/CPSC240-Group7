import java.util.Comparator;

public class StringComparator implements Comparator<BikePart> {

	@Override
	public int compare(BikePart o1, BikePart o2) {
		return o1.getName().compareTo(o2.getName());
		
	}
	

}
