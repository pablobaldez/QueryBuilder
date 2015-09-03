package querybuilder.sqlite.criteria;

import querybuilder.sqlite.projection.AliasedProjection;
import querybuilder.sqlite.projection.Projection;

import java.util.ArrayList;
import java.util.List;

public class InCriteria extends Criteria {
	private Projection projection;
	private List<Object> valuesList;
	private Object[] valuesArray;
	
	public InCriteria(Projection projection, List<Object> values) {
		this.projection = projection;
		this.valuesList = values;
		this.valuesArray = null;
		
		if(this.projection instanceof AliasedProjection)
			this.projection = ((AliasedProjection)this.projection).removeAlias();
	}
	
	public InCriteria(Projection projection, Object[] values) {
		this.projection = projection;
		this.valuesArray = values;
		this.valuesList = null;
		
		if(this.projection instanceof AliasedProjection)
			this.projection = ((AliasedProjection)this.projection).removeAlias();
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		
		if(projection != null)
			sb.append(projection.build());
		
		sb.append(" IN (");

		if(valuesList != null) {
			if(valuesList.size() <= 0)
				return "1=0";
			
			for (int i = 0; i < valuesList.size(); i++) {
				if(valuesList.get(i) != null)
					sb.append("?, ");
				else
					sb.append("NULL, ");
			}
		} else {
			if(valuesArray.length <= 0)
				return "1=0";

			for (Object aValuesArray : valuesArray) {
				if (aValuesArray != null)
					sb.append("?, ");
				else
					sb.append("NULL, ");
			}
		}
					
		sb.setLength(sb.length() - 2); //removes the ", " from the last entry
		sb.append(")");
		
		return sb.toString();
	}

	@Override
	public List<Object> buildParameters() {
		List<Object> ret = new ArrayList<>();
		
		if(projection != null)
			ret.addAll(projection.buildParameters());
		
		if(valuesList != null) {
			for (int i = 0; i < valuesList.size(); i++) {
				if(valuesList.get(i) != null)
					ret.add(valuesList.get(i));
			}
		} else {
			for (Object aValuesArray : valuesArray) {
				if (aValuesArray != null)
					ret.add(aValuesArray);
			}
		}
		
		return ret;
	}
}
