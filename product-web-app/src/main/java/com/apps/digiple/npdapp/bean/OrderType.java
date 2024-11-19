package com.apps.digiple.npdapp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "order_type")
public class OrderType {
    @Id
    @JsonProperty("UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "ord_type_key")
	private int key;

    @JsonProperty("Order Type Name")
    @Column(name = "ord_type_name")
	private String ordTypeName;

    @JsonProperty("Short Name")
    @Column(name = "short_name")
	private String shortName;

    @Column(name = "ord_type_details")
	private String ordTypeDetails;

    @Column(name = "creation_time")
	private String creationTime;

    @Column(name = "last_modified_time")
	private String lastModifiedTime;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getOrdTypeName() {
		return ordTypeName;
	}

	public void setOrdTypeName(String ordTypeName) {
		this.ordTypeName = ordTypeName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getOrdTypeDetails() {
		return ordTypeDetails;
	}

	public void setOrdTypeDetails(String ordTypeDetails) {
		this.ordTypeDetails = ordTypeDetails;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Override
    public String toString() {
		StringBuilder sb  = new StringBuilder();
		sb.append("OrderType{ ");
		sb.append(key);
		sb.append(", ");
		sb.append(ordTypeName);
		sb.append(", ");
		sb.append(ordTypeDetails);
		sb.append(" }");
        return sb.toString();
    }

}