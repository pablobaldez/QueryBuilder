package querybuilder.sqlite.order;

import querybuilder.Utils;
import querybuilder.sqlite.projection.Projection;

import java.util.List;

public class OrderDescendingIgnoreCase extends Order {

	public OrderDescendingIgnoreCase(Projection projection) {
		super(projection);
	}

	@Override
	public String build() {
		String ret = " COLLATE NOCASE DESC";
		
		if(projection != null)
			ret = projection.build() + ret;
		
		return ret;
	}

	@Override
	public List<Object> buildParameters() {
		if(projection != null)
			return projection.buildParameters();
		else
			return Utils.EMPTY_LIST;
	}
}
