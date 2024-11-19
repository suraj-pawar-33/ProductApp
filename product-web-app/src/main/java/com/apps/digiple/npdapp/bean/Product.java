package com.apps.digiple.npdapp.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class Product{
	
    @Id
    @JsonProperty("Product UID")
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(nullable = false, unique = true, name = "product_key")
    private int key;
    

    @JsonProperty("Product Name")
    @Column(nullable = false, unique = true, name = "product_name")
    private String productName;

    @JsonProperty("Product Details")
    @Column(nullable = false, unique = true, name = "product_details")
    private String productDetails;

    @JsonProperty("Short Name")
    @Column(nullable = true, unique = true, name = "short_name")
    private String shortName;

    @JsonProperty("Cost")
    @Column(nullable = true, unique = true, name = "cost")
    private int cost;

    @JsonProperty("Subscription Cost")
    @Column(nullable = true, unique = true, name = "subscri_cost")
    private int subscriCost;

    @JsonIgnore
    @Column(nullable = true, name = "pro_type_key")
    private int proTypeKey;


//    @JsonProperty("Creation Time")
    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = true, unique = true, name = "creation_time")
    private LocalDateTime creationTime;

//    @JsonProperty("Last Modified Time")
    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = true, unique = true, name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pro_type_key", insertable=false, updatable = false, referencedColumnName = "pro_type_key")
    private ProductType proType;

    @JsonProperty("Product Type")
    @Transient
	private String proTypeString;

    
    public Product() {  }

    public Product(String productName, int cost) {
    	this.setProductName(productName);
    	this.setCost(cost);
    }

    public Product(int id, String productName, int cost) {
    	this.setKey(id);
    	this.setProductName(productName);
    	this.setCost(cost);
    }

	public String getProTypeString() {
		return proType.getProductTypeName();
	}


	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSubscriCost() {
		return subscriCost;
	}

	public void setSubscriCost(int subscriCost) {
		this.subscriCost = subscriCost;
	}

	public int getProTypeKey() {
		return proTypeKey;
	}

	public void setProTypeKey(int proTypeKey) {
		this.proTypeKey = proTypeKey;
	}

	public LocalDateTime  getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Override
    public String toString() {
        return "Product{" +
                "id=" + key +
                ", name='" + productName + "'" +
                ", details='" + productDetails + "'" +
                ", cost='" + cost + "'" +
                '}';
    }

}
