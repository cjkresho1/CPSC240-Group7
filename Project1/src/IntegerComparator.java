import java.util.Comparator;

public class IntegerComparator implements Comparator<BikePart> {

	@Override
	public int compare(BikePart o1, BikePart o2) {
		return (o1.getNumber()-o2.getNumber());
		
	}
	

}