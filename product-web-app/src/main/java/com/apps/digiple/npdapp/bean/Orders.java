package com.apps.digiple.npdapp.bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "orders")
public class Orders{
	
    @Id
    @JsonProperty("Product UID")
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(nullable = false, unique = true, name = "order_key")
    private int key;
    

    @JsonProperty("Bill Number")
    @Column(nullable = false, unique = true, name = "bill_number")
    private int billNumber;

    @JsonProperty("Date Order Placed")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, unique = true, name = "date_order_placed")
    private Timestamp dateOrderPlaced;

    @JsonProperty("Date Order Paid")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = true, unique = true, name = "date_order_paid")
    private Timestamp dateOrderPaid;

    @JsonIgnore
    @Column(nullable = false, unique = true, name = "bank_key")
    private int bankKey;

    @JsonIgnore
    @Column(nullable = true, unique = true, name = "ord_type_key")
    private int ordTypeKey;

    @JsonProperty("Total Amount")
    @Column(nullable = true, unique = true, name = "total_amount")
    private int totalAmount;

    @JsonIgnore
    @Column(nullable = true, unique = true, name = "status_key")
    private int statusKey;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = true, unique = true, name = "creation_time")
    private LocalDateTime creationTime;

    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = true, unique = true, name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
    
    @ManyToOne
    @JoinColumn(name = "bank_key", insertable=false, updatable = false, referencedColumnName = "bank_key")
    private Bank bank;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ord_type_key", insertable=false, updatable = false, referencedColumnName = "ord_type_key")
    private OrderType orderType;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "status_key", insertable=false, updatable = false, referencedColumnName = "status_key")
    private Status status;
    
    @OneToMany
    @JoinColumn(name = "order_key", insertable=false, updatable = false, referencedColumnName = "order_key")
    private List<Order2Product> order2product;

    
    public List<Order2Product> getOrder2product() {
		return order2product;
	}

	public void setOrder2product(List<Order2Product> order2product) {
		this.order2product = order2product;
	}

	public Orders() {  }
    
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public String getDateOrderPlaced() {
		return dateOrderPlaced != null ? dateOrderPlaced.toString() : null;
	}

	public void setDateOrderPlaced(String dateOrderPlaced) {
		this.dateOrderPlaced = Timestamp.valueOf(LocalDateTime.parse(dateOrderPlaced));;
	}

	public String getDateOrderPaid() {
		return dateOrderPaid != null ? dateOrderPaid.toString() : null;
	}

	public void setDateOrderPaid(String dateOrderPaid) {
		this.dateOrderPaid = Timestamp.valueOf(LocalDateTime.parse(dateOrderPaid));
	}

	public int getBankKey() {
		return bankKey;
	}

	public void setBankKey(int bankKey) {
		this.bankKey = bankKey;
	}

	public int getOrdTypeKey() {
		return ordTypeKey;
	}

	public void setOrdTypeKey(int ordTypeKey) {
		this.ordTypeKey = ordTypeKey;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getStatusKey() {
		return statusKey;
	}

	public void setStatusKey(int statusKey) {
		this.statusKey = statusKey;
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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "Product{" +
                "id=" + key +
                ", billNo='" + billNumber + "'" +
                ", bank='" + bank + "'" +
                ", placedOn='" + dateOrderPlaced + "'" +
                ", paidOn='" + dateOrderPaid + "'" +
                ", totalAmount='" + totalAmount + "'" +
                '}';
    }

}
