package com.apps.digiple.npdapp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @JsonProperty("UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "pro_type_key")
	private int key;
    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy="proType")
//    private Product product;

    @JsonProperty("Product Type Name")
    @Column(name = "pro_type_name")
	private String productTypeName;

    @JsonProperty("Short Name")
    @Column(name = "short_name")
	private String shortName;

    @Column(name = "pro_type_details")
	private String productTypeDetails;

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

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProductTypeDetails() {
		return productTypeDetails;
	}

	public void setProductTypeDetails(String productTypeDetails) {
		this.productTypeDetails = productTypeDetails;
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
		sb.append("ProductType{ ");
		sb.append(key);
		sb.append(", ");
		sb.append(productTypeName);
		sb.append(", ");
		sb.append(productTypeDetails);
		sb.append(" }");
        return sb.toString();
    }

}
