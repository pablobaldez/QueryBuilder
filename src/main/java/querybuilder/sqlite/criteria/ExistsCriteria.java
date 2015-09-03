package querybuilder.sqlite.criteria;

import querybuilder.Utils;
import querybuilder.sqlite.QueryBuilder;

import java.util.List;

public class ExistsCriteria extends Criteria {
	private QueryBuilder subQuery;
	
	public ExistsCriteria(QueryBuilder subQuery) {
		this.subQuery = subQuery;
	}

	@Override
	public String build() {
		String ret = "EXISTS(";
		
		if(subQuery != null)
			ret = ret + subQuery.build();
		
		ret = ret + ")";
		return ret;
	}

	@Override
	public List<Object> buildParameters() {
		if(subQuery != null)
			return subQuery.buildParameters();
		else
			return Utils.EMPTY_LIST;
	}
}
