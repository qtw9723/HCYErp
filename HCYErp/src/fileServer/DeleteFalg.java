package fileServer;

public enum DeleteFalg {
	success,
	fail;
	
	@Override
	public String toString() {
		String name = null;
		if(this == success) {
			name = "success";
		}else {
			name = "fail";
		}//else
		return name;
	}
}//enum
