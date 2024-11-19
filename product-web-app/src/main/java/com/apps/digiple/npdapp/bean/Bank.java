package com.apps.digiple.npdapp.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "bank")
public class Bank{
	
    @Id
    @JsonProperty("Bank UID")
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(nullable = false, unique = true, name = "bank_key")
    private int key;
    

    @JsonProperty("Bank Name")
    @Column(nullable = false, unique = true, name = "bank_name")
    private String bankName;

    @JsonProperty("Branch Name")
    @Column(nullable = false, unique = true, name = "branch_name")
    private String branch;

    @JsonProperty("Short Name")
    @Column(nullable = true, unique = true, name = "short_name")
    private String shortName;


    @JsonProperty("Address Key")
    @Column(nullable = true, insertable=false, updatable=false, name = "address_key")
    private int addressKey;

    @JsonProperty("Creation Time")
    @Column(nullable = true, unique = true, name = "creation_time")
    private String creationTime;

    @JsonProperty("Last Modified Time")
    @Column(nullable = true, name = "last_modified_time")
    private String lastModifiedTime;
    

    
//    @OneToOne(fetch=FetchType.LAZY, mappedBy = "key")
////    @JoinColumn(name = "bank_key", referencedColumnName = "object_key")
//    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_key", referencedColumnName = "address_key")
    private Address address;


    @JsonProperty("Address")
    @Transient
	private String adddressString;
    
    public Bank() {  }

    public Bank(String bank_name, String branch_name) {
        this.setBankName(bank_name);
        this.setBranch(branch_name);
    }

    public Bank(int id, String bank_name, String branch_name) {
        this.setKey(id);
        this.setBankName(bank_name);
        this.setBranch(branch_name);
    }

	public void setKey(int id) {
		this.key = id;
	}

	 public int getKey() {
		return key;
	}

	public void setBankName(String bank) {
		this.bankName = bank;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBranch() {
		return branch;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getAddressKey() {
		return addressKey;
	}

	public void setAddressKey(int i) {
		this.addressKey = i;
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


	public Address getAddress() {
		return address;
	}
	

    @JsonProperty("Address Value")
	public String getAddressString() {
		return address.toString();
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
    public String toString() {
        return "Bank{" +
                "id=" + key +
                ", name='" + bankName + "'" +
                ", branch='" + branch + "'" +
                ", address='" + getAddressString() + "'" +
                '}';
    }

}
