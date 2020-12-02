package bid.google.pojos;

public class RawLocation {
	
	private String code;
	private String address;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address.replaceAll(" ", "|");
	}
	

}
