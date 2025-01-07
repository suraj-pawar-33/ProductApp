package com.apps.digiple.npdapp.models;

import java.util.Map;

import com.apps.digiple.npdapp.table.edit.FieldDataType;

/**
 * Hold the properties of a bean
 * 
 */
public class GeneralDataDefination {
	protected Map<String, String> columnNames;
	protected Map<String, FieldDataType> fieldDef;
	private String dbTableName;
	
	public GeneralDataDefination(Map<String, String> columnNames,
			Map<String, FieldDataType> fieldDef, String dbTableName) {
		super();
		this.columnNames = columnNames;
		this.fieldDef = fieldDef;
		this.dbTableName = dbTableName;
	}

	public Map<String, String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(Map<String, String> columnNames) {
		this.columnNames = columnNames;
	}

	public Map<String, FieldDataType> getFieldDef() {
		return fieldDef;
	}

	public void setFieldDef(Map<String, FieldDataType> fieldDef) {
		this.fieldDef = fieldDef;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}

}
