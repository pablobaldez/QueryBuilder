package querybuilder.sqlite.from;

import querybuilder.Utils;
import querybuilder.sqlite.QueryBuilder;

import java.util.List;

public class SubQueryFrom extends AliasableFrom<SubQueryFrom> {
	private QueryBuilder subQuery;

	public SubQueryFrom(QueryBuilder subQuery) {
		this.subQuery = subQuery;
	}
	
	@Override
	public String build() {
		String ret = (subQuery != null ? "(" + subQuery.build() + ")" : "");
		
		if(!Utils.isNullOrWhiteSpace(alias))
			ret = ret + " AS " + alias;
		
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
