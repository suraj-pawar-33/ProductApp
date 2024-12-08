package com.apps.digiple.npdapp.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "order2product")
public class OrderToProduct {
	
	  @Id
	    @JsonProperty("order_key ")
	    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	    @GenericGenerator(name = "native",strategy = "native")
	    @Column(nullable = false, unique = true, name = "order_key ")
	    private int order_key ;
	  
	
	    @JsonProperty("product_key")
	    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	    @GenericGenerator(name = "native",strategy = "native")
	    @Column(nullable = false, unique = true, name = "product_key")
	    private int product_key; 
	 
	   @JsonProperty("Quantity")
	    @Column(nullable = false, unique = true, name = "quantity")
	    private int quantity;

	  @JsonProperty("Amount")
	    @Column(nullable = false, unique = true, name = "amount")
	    private int amount;
	 
	 
//   @JsonProperty("Creation Time")
   @JsonIgnore
   @CreationTimestamp
   @Column(nullable = true, unique = true, name = "creation_time")
   private LocalDateTime creationTime;

//   @JsonProperty("Last Modified Time")
   @JsonIgnore
   @LastModifiedDate
   @Column(nullable = true, unique = true, name = "last_modified_time")
   private LocalDateTime lastModifiedTime;

public int getOrder_key() {
	return order_key;
}

public void setOrder_key(int order_key) {
	this.order_key = order_key;
}

public int getProduct_key() {
	return product_key;
}

public void setProduct_key(int product_key) {
	this.product_key = product_key;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public LocalDateTime getCreationTime() {
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
   
   
	
	

}
