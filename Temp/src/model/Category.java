package model;

public class Category {

	private long id;
	private String name;
	private String familyName;
	private Category parent;
	// private ArrayList<Category> children; // potrebbe non servire

	public Category(long id, String name, Category parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.familyName = buildFamilyName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public boolean equals(Object obj) {
		Category cat = (Category) obj;
		return this.name.equals(cat.name);
	}

	public String getFamilyName() {
		return familyName;
	}

	public Category getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return familyName;
	}

}
