package com.apps.digiple.npdapp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @JsonProperty("UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "status_key")
	private int key;

    @JsonProperty("Status Name")
    @Column(name = "status_name")
	private String statusName;

    @Column(name = "status_descr")
	private String statusDescr;

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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusDescr() {
		return statusDescr;
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
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
		sb.append("Status{ ");
		sb.append(key);
		sb.append(", ");
		sb.append(statusName);
		sb.append(", ");
		sb.append(statusDescr);
		sb.append(" }");
        return sb.toString();
    }

}