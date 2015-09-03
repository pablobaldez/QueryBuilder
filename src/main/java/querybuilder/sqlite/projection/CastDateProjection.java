package querybuilder.sqlite.projection;

import querybuilder.Utils;

import java.util.List;

public class CastDateProjection extends Projection {
	private Projection projection;
	
	public CastDateProjection(Projection projection) {
		this.projection = projection;
	}
	
	@Override
	public String build() {
		String ret = (projection != null ? projection.build() : "");
		return "DATE(" + ret + ")";
	}

	@Override
	public List<Object> buildParameters() {
		if(projection != null)
			return projection.buildParameters();
		else
			return Utils.EMPTY_LIST;
	}
}
