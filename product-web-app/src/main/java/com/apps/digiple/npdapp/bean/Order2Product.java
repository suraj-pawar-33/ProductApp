package com.apps.digiple.npdapp.bean;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "order2product")
public class Order2Product {

	@Id
	@JsonProperty("Order2Product UID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(nullable = false, unique = true, name = "ord2pro_key")
	private int key;

	@JsonProperty("Order key")
	@Column(nullable = false, unique = true, name = "order_key ")
	private int orderKey;

	@JsonProperty("Product key")
	@Column(nullable = false, unique = true, name = "product_key")
	private int productKey;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_key", insertable=false, updatable = false, referencedColumnName = "product_key")
	private Product product;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_key", insertable=false, updatable = false, referencedColumnName = "order_key")
	private Orders order;

	@JsonProperty("Quantity")
	@Column(nullable = false, unique = true, name = "quantity")
	private int quantity;

	@JsonProperty("Amount")
	@Column(nullable = false, unique = true, name = "amount")
	private int amount;

	@JsonProperty("Creation Time")
//	@JsonIgnore
	@Transient
	@Column(nullable = true, unique = true, name = "creation_time")
	private LocalDateTime creationTime;

	@JsonProperty("Last Modified Time")
//	@JsonIgnore
	@Transient
	@Column(nullable = true, unique = true, name = "last_modified_time")
	private LocalDateTime lastModifiedTime;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(int order_key) {
		this.orderKey = order_key;
	}

	public int getProductKey() {
		return productKey;
	}

	public void setProductKey(int product_key) {
		this.productKey = product_key;
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

	public Product getProduct() {
		return product;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}
}
