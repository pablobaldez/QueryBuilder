package querybuilder.sqlite.criteria;

import java.util.ArrayList;
import java.util.List;

public class AndCriteria extends Criteria {
	private Criteria left;
	private Criteria right;
	
	public AndCriteria(Criteria left, Criteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String build() {
		String ret = " AND ";
		
		if(left != null)
			ret = left.build() + ret;
		
		if(right != null)
			ret += right.build();

		return "(" + ret.trim() + ")";
	}

	@Override
	public List<Object> buildParameters() {
		List<Object> ret = new ArrayList<>();
		
		if(left != null)
			ret.addAll(left.buildParameters());
		
		if(right != null)
			ret.addAll(right.buildParameters());
		
		return ret;
	}
}
