package com.guoan.entity.shequ.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeStructure implements Serializable {

	private static final long serialVersionUID = -4955389092997633895L;
	
	private String id;
	
	private String name;
	
	private String level;
	
	private List<TreeStructure> children = new ArrayList<TreeStructure>();

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

	public List<TreeStructure> getChildren() {
		return children;
	}

	public void setChildren(List<TreeStructure> children) {
		this.children = children;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
