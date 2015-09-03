package querybuilder.sqlite.projection;

import querybuilder.Utils;
import querybuilder.sqlite.QueryBuilder;

import java.util.List;

public class SubQueryProjection extends Projection {
	private QueryBuilder subQuery;
	
	public SubQueryProjection(QueryBuilder subQuery) {
		this.subQuery = subQuery;
	}

	@Override
	public String build() {
		if(subQuery != null)
			return "(" + subQuery.build() + ")";
		else
			return "";
	}

	@Override
	public List<Object> buildParameters() {
		if(subQuery != null)
			return subQuery.buildParameters();
		else
			return Utils.EMPTY_LIST;
	}
}
