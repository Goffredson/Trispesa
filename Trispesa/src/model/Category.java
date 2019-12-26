package model;

import java.util.ArrayList;

public class Category {

	private int ID;
	private String name;
	private Category parent;
	private ArrayList<Category> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public ArrayList<Category> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Category> children) {
		this.children = children;
	}
	
	public void addChidren(Category child) {
		this.children.add(child);
	}
	
	public void removeChidren(Category child) {
		this.children.remove(child);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Category) {
			Category category = (Category) obj;
			return this.ID == category.ID;
		}
		return false;
	}

}
