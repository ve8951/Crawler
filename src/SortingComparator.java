import java.util.Comparator;


public class SortingComparator implements Comparator<ModelExcell>{

	public int compare(ModelExcell o1, ModelExcell o2) {
		return o1.getProjName().compareTo(o2.getProjName());
	}

	
}
