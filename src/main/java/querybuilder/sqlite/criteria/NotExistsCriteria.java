package querybuilder.sqlite.criteria;

import querybuilder.sqlite.QueryBuilder;

public class NotExistsCriteria extends ExistsCriteria {
	public NotExistsCriteria(QueryBuilder subQuery) {
		super(subQuery);
	}
	
	@Override
	public String build() {
		return "NOT " + super.build();
	}
}
