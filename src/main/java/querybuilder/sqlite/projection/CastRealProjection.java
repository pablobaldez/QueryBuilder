package querybuilder.sqlite.projection;

import querybuilder.Utils;

import java.util.List;

public class CastRealProjection extends Projection {
	private Projection projection;

	public CastRealProjection(Projection projection) {
		this.projection = projection;
	}

	@Override
	public String build() {
		String ret = (projection != null ? projection.build() : "");
		return "CAST(" + ret + " AS REAL)";
	}

	@Override
	public List<Object> buildParameters() {
		if (projection != null)
			return projection.buildParameters();
		else
			return Utils.EMPTY_LIST;
	}
}
