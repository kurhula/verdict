package edu.umich.verdict.datatypes;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import edu.umich.verdict.VerdictJDBCContext;

public class VerdictApproxResultMeta {
	
	VerdictJDBCContext vc;
	
	private Map<Integer, Integer> aggColumn2ErrorColumn;
	
	private Map<Alias, String> aliasToColumnLabel;
	
	private ResultSet rs;

	public VerdictApproxResultMeta(
			Map<Integer, Integer> aggColumn2ErrorColumn,
			Map<Alias, String> aliasToColumnLabel,
			ResultSet rs) {
		this.aggColumn2ErrorColumn = aggColumn2ErrorColumn;
		this.aliasToColumnLabel = aliasToColumnLabel;
		this.rs = rs;
	}
	
	public String getColumnLabel(int columnIndex) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		String aliasString = meta.getColumnLabel(columnIndex);		// either autogenerated or explicitly specified
		String baseName = aliasString;
		
		for (Map.Entry<Alias, String> e : aliasToColumnLabel.entrySet()) {
			if (e.getKey().aliasName().equals(aliasString)) {
				if (e.getKey().autoGenerated()) {
					baseName = e.getKey().originalName();
				} else {
					baseName = e.getKey().aliasName();
				}
			}
		}

		return baseName;
	}

}
