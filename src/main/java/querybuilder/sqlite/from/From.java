package querybuilder.sqlite.from;

import querybuilder.sqlite.QueryBuilder;
import querybuilder.sqlite.criteria.Criteria;
import querybuilder.sqlite.projection.Projection;

import java.util.List;


public abstract class From {
	public static class PartialJoin {
		private String joinType;
		private From left;
		private From right;

		protected PartialJoin(From left, From right, String joinType) {
			this.joinType = joinType;
			this.left = left;
			this.right = right;
		}
		
		public JoinFrom on(String leftColumn, String rightColumn) {
			return on(Criteria.equals(Projection.column(leftColumn), Projection.column(rightColumn)));
		}

		public JoinFrom on(Projection leftProjection, Projection rightProjection) {
			return on(Criteria.equals(leftProjection, rightProjection));
		}
		
		public JoinFrom on(Criteria criteria) {
			return new JoinFrom(left, right, joinType, criteria);
		}
	}
	
	public static TableFrom table(String table) {
		return new TableFrom(table);
	}
	
	public static SubQueryFrom subQuery(QueryBuilder subQuery) {
		return new SubQueryFrom(subQuery);
	}
	
	public PartialJoin innerJoin(String table) {
		return innerJoin(From.table(table));
	}
	
	public PartialJoin innerJoin(QueryBuilder subQuery) {
		return innerJoin(From.subQuery(subQuery));
	}
	
	public PartialJoin innerJoin(From table) {
		return new PartialJoin(this, table, "INNER JOIN");
	}
	
	public PartialJoin leftJoin(String table) {
		return leftJoin(From.table(table));
	}
	
	public PartialJoin leftJoin(QueryBuilder subQuery) {
		return leftJoin(From.subQuery(subQuery));
	}
	
	public PartialJoin leftJoin(From table) {
		return new PartialJoin(this, table, "LEFT JOIN");
	}
	
	public abstract String build();
	
	public abstract List<Object> buildParameters();
}
