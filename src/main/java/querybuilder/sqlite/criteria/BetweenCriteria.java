package querybuilder.sqlite.criteria;

import querybuilder.sqlite.projection.AliasedProjection;
import querybuilder.sqlite.projection.Projection;

import java.util.ArrayList;
import java.util.List;

public class BetweenCriteria extends Criteria {
	private Projection projection;
	private Object valueStart;
	private Object valueEnd;
	
	public BetweenCriteria(Projection projection, Object valueStart, Object valueEnd) {
		this.projection = projection;
		this.valueStart = valueStart;
		this.valueEnd = valueEnd;
		
		if(this.projection instanceof AliasedProjection)
			this.projection = ((AliasedProjection)this.projection).removeAlias();
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		
		if(projection != null)
			sb.append(projection.build());
		
		sb.append(" BETWEEN ");
		sb.append((valueStart != null ? "?" : "NULL"));
		sb.append(" AND ");
		sb.append((valueEnd != null ? "?" : "NULL"));
		
		return sb.toString();
	}

	@Override
	public List<Object> buildParameters() {
		List<Object> ret = new ArrayList<>();
		
		if(projection != null)
			ret.addAll(projection.buildParameters());
		
		if(valueStart != null)
			ret.add(valueStart);
		
		if(valueEnd != null)
			ret.add(valueEnd);
		
		return ret;
	}
}
