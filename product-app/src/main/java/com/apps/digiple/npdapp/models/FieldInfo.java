package com.apps.digiple.npdapp.models;

import com.apps.digiple.npdapp.table.edit.FieldDataType;

public class FieldInfo {

	private String lableText;
	private String columnName;
	private FieldDataType fieldDataType;
	private boolean isVisible = true;
	private boolean isEditable = true;
	private String propertyName;
	
	public FieldInfo(String textField, String columnName, String propertyName, FieldDataType fieldDataType) {
		super();
		this.lableText = textField;
		this.columnName = columnName;
		this.propertyName = propertyName;
		this.fieldDataType = fieldDataType;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getLableText() {
		return lableText;
	}
	public void setLableText(String lableText) {
		this.lableText = lableText;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public FieldDataType getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(FieldDataType fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public boolean isEditable() {
		return isEditable;
	}
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
}
