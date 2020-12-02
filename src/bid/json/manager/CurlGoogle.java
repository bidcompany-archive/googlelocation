package bid.json.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bid.google.props.Envs;
import bid.google.pojos.Location;
import bid.google.pojos.RawLocation;

public class CurlGoogle {
	private static final Logger logger = LoggerFactory.getLogger(CurlGoogle.class);
	private String urlG;
	private Envs env;
	private int wait;
	
	public CurlGoogle() {
		this.env = new Envs();
		this.urlG = env.getApiep() + "&key=" + env.getApikey();
		this.wait = env.getWait();
	}
	
	// Input :: RAWLOCATION POJO 
	// data[0] : code
	// data[1] : address
	
	// Output :: LOCATION POJO 
	// res[0] : code
	// res[1] : address formatted by google
	// res[2] : lat 
	// res[3] : lon
	
	public List<Location> getCoordinates(List<RawLocation> places) {
		List<Location> ret = new ArrayList<Location>();
		logger.debug("Quering for : [" + places.size() + "] places.");
		for(RawLocation locStr : places) {
			ret.add(getLocation(locStr.getCode(), locStr.getAddress()));
		}
		return ret;
	}
	
	
	private Location getLocation(String code, String address) {
		Location ret = null;
		try {
			URL url = new URL(urlG + "&address=" + address);
	    	String resp = ""; 
	    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) { 
	    	    for (String line; (line = reader.readLine()) != null;) {
	    	    	resp += line; 
	    	    }
	    	}
	    	JSONObject obj = new JSONObject(resp);
	    	JSONArray arr = obj.getJSONArray("results");
	    	String formatted_address  = "";
	    	Float lat = null;
	    	Float lon = null;
	    	
	    	for (int i = 0; i < arr.length(); i++){
	    		
	    		JSONObject location = arr.getJSONObject(i); 
	    	    formatted_address = location.getString("formatted_address");
	    	    lat = location.getJSONObject("geometry").getJSONObject("location").getFloat("lat");
	    	    lon = location.getJSONObject("geometry").getJSONObject("location").getFloat("lng");
	    	}
	    	ret = new Location();
	    	ret.setCode(code);
	    	ret.setAddress(formatted_address);
	    	ret.setLat(lat);
	    	ret.setLng(lon);
	    	
	    	logger.debug(ret.toString(env.getSeparator()));
	    	
	    	Thread.sleep(wait * 1000);
	    } catch (Exception e) { 
	    	e.printStackTrace(); 
	    } 
		return ret;
	}
}
