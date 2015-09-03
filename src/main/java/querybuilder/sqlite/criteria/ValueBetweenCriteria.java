package querybuilder.sqlite.criteria;

import querybuilder.sqlite.projection.AliasedProjection;
import querybuilder.sqlite.projection.Projection;

import java.util.ArrayList;
import java.util.List;

public class ValueBetweenCriteria extends Criteria {
	private Object value;
	private Projection projectionStart;
	private Projection projectionEnd;
	
	public ValueBetweenCriteria(Object value, Projection projectionStart, Projection projectionEnd) {
		this.value = value;
		this.projectionStart = projectionStart;
		this.projectionEnd = projectionEnd;
		
		if(this.projectionStart instanceof AliasedProjection)
			this.projectionStart = ((AliasedProjection)this.projectionStart).removeAlias();
		
		if(this.projectionEnd instanceof AliasedProjection)
			this.projectionEnd = ((AliasedProjection)this.projectionEnd).removeAlias();
	}
	
	@Override
	public String build() {

		return (value != null ? "?" : "NULL") + " BETWEEN " +
				(projectionStart != null ? projectionStart.build() : "NULL") + " AND "
				+ (projectionEnd != null ? projectionEnd.build() : "NULL");
	}

	@Override
	public List<Object> buildParameters() {
		List<Object> ret = new ArrayList<>();
		
		if(value != null)
			ret.add(value);
		
		if(projectionStart != null)
			ret.addAll(projectionStart.buildParameters());
		
		if(projectionEnd != null)
			ret.addAll(projectionEnd.buildParameters());
		
		return ret;
	}
}
