package login;

public class AddVO {
	private String columnName;
	private String value;
	
	public AddVO() {
	}

	public AddVO(String columnName, String value) {
		this.columnName = columnName;
		this.value = value;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}//class
