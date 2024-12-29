package com.apps.digiple.npdapp.reports;

public class JasperProduct {

    private String productName;

    private String productDetail;
    
	private int qntyEntry;
	
	private int productCost;
    
	private int productType;
	
	private int shortName;
    
	private int proSubCost;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetails) {
		this.productDetail = productDetails;
	}

	public int getQntyEntry() {
		return qntyEntry;
	}

	public void setQntyEntry(int i) {
		this.qntyEntry = i;
	}

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int i) {
		this.productCost = i;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public int getShortName() {
		return shortName;
	}

	public void setShortName(int shortName) {
		this.shortName = shortName;
	}

	public int getProSubCost() {
		return proSubCost;
	}

	public void setProSubCost(int proSubCost) {
		this.proSubCost = proSubCost;
	}
}
