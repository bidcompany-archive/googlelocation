package bid.google.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bid.csv.manager.Csv;
import bid.google.pojos.Location;
import bid.json.manager.CurlGoogle;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private CurlGoogle curl;
	private Csv csv;
	
	public Main() {
		curl = new CurlGoogle();
		csv = new Csv();
	}
	
	public void start() {
		logger.info("Started ... ");
		List<Location> places = curl.getCoordinates(csv.readAddress());
		csv.saveAddress(places);
		logger.info(" ... completed!");
	}
	
	
	
	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
	
	
	
	
	
	
	
	
	
	
	
	public void old(String[] args) {
		String codice = "";
		String indirizzo = "";
		String google = "https://maps.googleapis.com/maps/api/geocode/json?components=country:IT&key=AIzaSyByKtSsH_F260XiHgq4NVuzCuFvmgRwJkw&address=";
		String printLn = "CODICE_STRUTTURA;INDIRIZZO_FORMATTATO;LAT;LON" ; 
		System.out.println(printLn);
		try {
			String row = "";
			BufferedReader csvReader = new BufferedReader(new FileReader("res/addresses.csv"));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(";");
			    
			    codice = data[9];
			    indirizzo = data[data.length-1];
			    
			    String urlG = google + indirizzo ; 
			    
			    
			    try {
			    	
			    	

			    	URL url = new URL(urlG.replaceAll(" ", "|")); 
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
			    	System.out.println(data.length-1);
			    	for (int i = 0; i < arr.length(); i++){
			    		JSONObject location = arr.getJSONObject(i); 
			    	    formatted_address = location.getString("formatted_address");
			    	    lat = location.getJSONObject("geometry").getJSONObject("location").getFloat("lat");
			    	    lon = location.getJSONObject("geometry").getJSONObject("location").getFloat("lng");
			    	}
			    	
			    	printLn = "\n" + codice + ";" + formatted_address + ";" + lat + ";" + lon ;
			    	System.out.println(printLn);
			    	

			    } catch (Exception e) { 
			    	e.printStackTrace(); 
			    } 
			    Thread.sleep(1000);
			    
			}
			csvReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
