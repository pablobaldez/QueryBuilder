package querybuilder.sqlite.from;

import querybuilder.Utils;

import java.util.List;


public class TableFrom extends AliasableFrom<TableFrom> {
	private String table;
	
	public TableFrom(String table) {
		this.table = table;
	}

	@Override
	public String build() {
		String ret = (!Utils.isNullOrWhiteSpace(table) ? table : "");

		if(!Utils.isNullOrWhiteSpace(alias))
			ret = ret + " AS " + alias;
		
		return ret;
	}

	@Override
	public List<Object> buildParameters() {
		return Utils.EMPTY_LIST;
	}
}
