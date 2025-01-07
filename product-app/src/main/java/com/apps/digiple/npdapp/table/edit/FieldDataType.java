package com.apps.digiple.npdapp.table.edit;

public enum FieldDataType {

	STRING_FIELD("String", String.class), LONG_FIELD("Long", Long.class);
	private String type;
	private Class<?> clazz;

	private FieldDataType(String type, Class<?> clazz) {
		this.type = type;
		this.clazz = clazz;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
