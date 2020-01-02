package model;

public class Category {

	private String name;
	private String familyName;
	private Category parent;
//	private ArrayList<Category> children; // potrebbe non servire

	public Category(String name, Category parent) {
		super();
		this.name = name;
		this.parent = parent;
		this.familyName = buildFamilyName();
	}

	private String buildFamilyName() {
		if (parent == null) {
			return this.name;
		}
		return parent.buildFamilyName() + " " + this.name;
	}

	public String getName() {
		return name;
	}

	public String getFamilyName() {
		return familyName;
	}

	public Category getParent() {
		return parent;
	}

}
