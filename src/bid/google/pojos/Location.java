package bid.google.pojos;

public class Location {
	
	private String code;
	private String address;
	private Float lat;
	private Float lng;
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
		this.address = address;
	}
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	
	public String toString(String sep) {
		String ret = "";
		ret += code + sep + address + sep + lat + sep + lng + sep ; 
		return ret ;
	}

}
