package querybuilder.sqlite.criteria;

import querybuilder.sqlite.projection.AliasedProjection;
import querybuilder.sqlite.projection.Projection;

import java.util.ArrayList;
import java.util.List;

public class BasicCriteria extends Criteria {
	public static class Operators {
		public static final String IS_NULL = "IS NULL";
		public static final String IS_NOT_NULL = "IS NOT NULL";
		public static final String EQUALS = "=";
		public static final String NOT_EQUALS = "<>";
		public static final String GREATER = ">";
		public static final String LESSER = "<";
		public static final String GREATER_OR_EQUALS = ">=";
		public static final String LESSER_OR_EQUALS = "<=";
		public static final String LIKE = "LIKE";
		public static final String NOT_LIKE = "NOT LIKE";
	}
	
	private Projection projection;
	private String operator;
	private Object value;
	
	public BasicCriteria(Projection projection, String operator, Object value) {
		this.projection = projection;
		this.operator = operator;
		this.value = value;
		
		if(this.projection instanceof AliasedProjection)
			this.projection = ((AliasedProjection)this.projection).removeAlias();
		
		if(value == null) {
			if(Operators.IS_NULL.equals(operator) || Operators.EQUALS.equals(operator) || Operators.LIKE.equals(operator))
				this.operator = Operators.IS_NULL;
			else
				this.operator = Operators.IS_NOT_NULL;
		}
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		
		if(projection != null) {
			/*
			if(value instanceof LocalDateTime)
				sb.append(projection.castAsDateTime().build());
			else if(value instanceof LocalDate)
				sb.append(projection.castAsDate().build());
			else
			*/
				sb.append(projection.build());
		}
		
		sb.append(" ");
		sb.append(operator);
		sb.append(" ");
		
		if(value != null) {
			if(value instanceof AliasedProjection)
				sb.append(((AliasedProjection)value).removeAlias().build());
			else if(value instanceof Projection)
				sb.append(((Projection)value).build());
			else
				sb.append("?");
		}
		
		return sb.toString();
	}

	@Override
	public List<Object> buildParameters() {
		List<Object> ret = new ArrayList<>();
		
		if(projection != null)
			ret.addAll(projection.buildParameters());
		
		if(value != null)
            if(value instanceof Projection)
                ret.addAll(((Projection) value).buildParameters());
            else ret.add(value);
		
		return ret;
	}
}
