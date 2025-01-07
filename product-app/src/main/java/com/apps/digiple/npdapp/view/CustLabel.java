package com.apps.digiple.npdapp.view;

import javafx.scene.text.Text;

public enum CustLabel {
	PRODUCT("Product")
	, BANK("Bank")
	, PRO_NAME("Product Name")
	
	, BK_NAME("Bank Name")
	, BK_BRANCH("Branch Name")
	, BK_SHNAME("Short Name")
	, BK_ADDRESS("Address")
	
	, BK_PRO_SHNAME("Short Name")
	, BK_DETAIL("Details")
	, BK_COST("Cost")
	, BK_LINE1("Line 1")
	, BK_LINE2("Line 2")
	, BK_LINE3("Line 3")
	, BK_OTHER("Other")
	, BK_CONT("Country")
	, BK_STATE("State")
	, BK_ZIP("Zip Code")
	, BK_CITY("City")
	, PRO_TYPE("Product Type")

	, CONTACT("Contact")
	, CONT_NAME("Contact Name")
	, CONT_BRANCH("Branch Name")
	, CONT_BK_SHNAME("Short Name")
	, CONT_ADDRESS("Address")

	, CONT_LINE1("Line 1")
	, CONT_LINE2("Line 2")
	, CONT_LINE3("Line 3")
	, CONT_OTHER("Other")
	, CONT_CONT("Country")
	, CONT_STATE("State")
	, CONT_ZIP("Zip Code")
	, CONT_CITY("City")
	, CONT_PRO_TYPE("Product Type")
	
	, ORDER("Order")
	, PLACED_DATE("Placed Date")
	, BILL_NUM("Bill Number")
	, ORDER_TYPE("Order Type")
	, BANK_NAME("Bank Name")
	, TOTAL_AMOUNT("Total Amount")
	, STATUS("Status")
	
	, SUBS_COST("Subscription Cost")
	, SUB_MONTH("Subscr Month")
	, SUB_BILL_NUM("Bill Number")
	, SUB_ORDER_TYPE("Order Type")
	, SUB_BANK_NAME("Bank Name")
	, SUB_TOTAL_AMOUNT("Total Amount")
	, SUB_STATUS("Status");
	
	private Text lebel;
	private Text boldLebel;
	private String lblText;

	private CustLabel(String lblText) {
		this.lblText = lblText;
		this.lebel = createLabel();
		this.boldLebel = createBoldLabel();
	}

	public Text createLabel() {
		Text text = new Text(lblText);
		text.setStrokeWidth(5);
		text.setFont(CustFontColor.getNormalFont());
		return text; 
	}
	public Text createBoldLabel() {
		Text text = new Text(lblText);
		text.setStrokeWidth(5);
		text.setFont(CustFontColor.getBoldFont());
		return text; 
	}

	public Text getLebel() {
		return lebel;
	}
	
	public Text getBoldLebel() {
		return boldLebel;
	}
	
	public static Text createLabel(String str) {
		Text text = new Text(str);
		text.setStrokeWidth(5);
		text.setFont(CustFontColor.getNormalFont());
		return text; 
	}

}
