package querybuilder.sqlite.criteria;

import java.util.ArrayList;
import java.util.List;

public class OrCriteria extends Criteria {
	private Criteria left;
	private Criteria right;
	
	public OrCriteria(Criteria left, Criteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String build() {
		String ret = " OR ";
		
		if(left != null)
			ret = left.build() + ret;
		
		if(right != null)
			ret = ret + right.build();
		
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
