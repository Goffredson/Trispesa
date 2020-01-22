package model;

public class OperationResult {

	private boolean result;
	private String type;
	private String object;
	private String state;

	public OperationResult() {
	}

	public OperationResult(boolean result, String type, String object, String state) {
		super();
		this.result = result;
		this.type = type;
		this.object = object;
		this.state = state;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
