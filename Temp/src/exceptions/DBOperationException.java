package exceptions;

public class DBOperationException extends Exception {

	private String object;

	public DBOperationException(String errorMessage, String object) {
		super(errorMessage);
		this.object = object;
	}

	public String getObject() {
		return object;
	}

}