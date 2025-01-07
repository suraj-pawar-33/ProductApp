package com.apps.digiple.npdapp.models;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import javafx.beans.property.SimpleStringProperty;

public class GeneralModel extends ItemModel {

	private static final Logger LOGGER = Logger.getLogger(GeneralModel.class);

	private SimpleStringProperty stringProperty01 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty02 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty03 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty04 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty05 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty06 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty07 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty08 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty09 = new SimpleStringProperty();
	private SimpleStringProperty stringProperty010 = new SimpleStringProperty();

	public GeneralModel(int key, String... stringProps) {
		super(key);
		try {
			for (int i = 1; i < stringProps.length; i++) {
				Method method = GeneralModel.class.getMethod("setStringProperty0" + i, String.class);
				method.invoke(this, stringProps[i]);
			}
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}

	public String getStringProperty01() {
		return stringProperty01.getValue();
	}

	public void setStringProperty01(String stringProperty01) {
		this.stringProperty01.setValue(stringProperty01);
	}

	public String getStringProperty02() {
		return stringProperty02.getValue();
	}

	public void setStringProperty02(String stringProperty02) {
		this.stringProperty02.setValue(stringProperty02);
	}

	public String getStringProperty03() {
		return stringProperty03.getValue();
	}

	public void setStringProperty03(String stringProperty03) {
		this.stringProperty03.setValue(stringProperty03);
	}

	public String getStringProperty04() {
		return stringProperty04.getValue();
	}

	public void setStringProperty04(String stringProperty04) {
		this.stringProperty04.setValue(stringProperty04);
	}

	public String getStringProperty05() {
		return stringProperty05.getValue();
	}

	public void setStringProperty05(String stringProperty05) {
		this.stringProperty05.setValue(stringProperty05);
	}

	public String getStringProperty06() {
		return stringProperty06.getValue();
	}

	public void setStringProperty06(String stringProperty06) {
		this.stringProperty06.setValue(stringProperty06);
	}

	public String getStringProperty07() {
		return stringProperty07.getValue();
	}

	public void setStringProperty07(String stringProperty07) {
		this.stringProperty07.setValue(stringProperty07);
	}

	public String getStringProperty08() {
		return stringProperty08.getValue();
	}

	public void setStringProperty08(String stringProperty08) {
		this.stringProperty08.setValue(stringProperty08);
	}

	public String getStringProperty09() {
		return stringProperty09.getValue();
	}

	public void setStringProperty09(String stringProperty09) {
		this.stringProperty09.setValue(stringProperty09);
	}

	public String getStringProperty010() {
		return stringProperty010.getValue();
	}

	public void setStringProperty010(String stringProperty010) {
		this.stringProperty010.setValue(stringProperty010);
	}
}
