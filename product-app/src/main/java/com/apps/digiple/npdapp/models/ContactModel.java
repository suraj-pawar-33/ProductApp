package com.apps.digiple.npdapp.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ContactModel extends ItemModel{

	public static final String CONTACTNAME = "contactName";

	public static final String BANKKEY = "bankKey";
	
	private final SimpleStringProperty contactName;
	private SimpleIntegerProperty bankKey;
	private final SimpleStringProperty address;

	private AddressModel addressModel;

	public ContactModel(int key, String contactName, int bankKey, AddressModel address) {
		super(key);
		this.contactName = new SimpleStringProperty(contactName);
		this.bankKey = new SimpleIntegerProperty(bankKey);
		this.address = new SimpleStringProperty(address != null ? address.getAddressString() : "");
		this.addressModel = address;
	}

	public int getContactKey() {
		return key.get();
	}

	public void setContactKey(int longValue) {
		key.set(longValue);
	}

	public String getContactName() {
		return contactName.get();
	}

	public void setContactName(String string) {
		contactName.set(string);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String string) {
		address.set(string);
	}
	
	public AddressModel address() {
		return addressModel;
	}

	public int getBankKey() {
		return bankKey.get();
	}

	public void setBankKey(int bankKey) {
		this.bankKey.set(bankKey);
	}

	

}
