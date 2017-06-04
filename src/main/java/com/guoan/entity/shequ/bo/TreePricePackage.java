package com.guoan.entity.shequ.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreePricePackage implements Serializable {

	private static final long serialVersionUID = 871100808297949875L;
	
	private String id;
	
	private String name;
	
	private String describe;
	
	private double price;
	
	private String providerId;
	
	private String providerName;

	private List<TreePricePackage> children = new ArrayList<TreePricePackage>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public List<TreePricePackage> getChildren() {
		return children;
	}

	public void setChildren(List<TreePricePackage> children) {
		this.children = children;
	}
	
}
