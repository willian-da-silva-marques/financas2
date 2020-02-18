package br.com.alura.financas.model;

public class QueryModel {

	private final String className;
	private final String columnName;
	private final String alias;

	public QueryModel(String className, String columnName, String alias) {
		this.className = className;
		this.columnName = columnName;
		this.alias = alias;
	}

	public String getClassName() {
		return className;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getAlias() {
		return alias;
	}
}
