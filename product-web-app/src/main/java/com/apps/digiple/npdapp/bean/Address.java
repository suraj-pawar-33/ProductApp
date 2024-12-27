package com.apps.digiple.npdapp.bean;

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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(nullable = false, unique = true, name = "address_key")
	private int key;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "object_key")
//    private Bank bank;
    
    @OneToOne(mappedBy = "address")
    private Bank bank;

    @Column(name = "line_1")
	private String line1;

    @Column(name = "line_2")
	private String line2;

    @Column(name = "line_3")
	private String line3;

    @Column(name = "city")
	private String city;

    @Column(name = "zip_postcode")
	private String zipCode;

    @Column(name = "state")
	private String state;

    @Column(name = "country")
	private String country;

    @Column(name = "other")
	private String other;

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "creation_time")
	private String addCreationTime;

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "last_modified_time")
	private String addLastModifiedTime;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getAddCreationTime() {
		return addCreationTime;
	}

	public void setAddCreationTime(String addCreationTime) {
		this.addCreationTime = addCreationTime;
	}

	public String getAddLastModifiedTime() {
		return addLastModifiedTime;
	}

	public void setAddLastModifiedTime(String addLastModifiedTime) {
		this.addLastModifiedTime = addLastModifiedTime;
	}

	@Override
    public String toString() {
		StringBuilder sb  = new StringBuilder();
		sb.append(line1);
		sb.append(", ");
		sb.append(line2);
		sb.append(", ");
		sb.append(line3);
		sb.append(", ");
		sb.append(city);
		sb.append("-");
		sb.append(zipCode);
		sb.append(", ");
		sb.append(state);
		sb.append(", ");
		sb.append(country);
		sb.append(", ");
		sb.append(other);
        return sb.toString();
    }

}
