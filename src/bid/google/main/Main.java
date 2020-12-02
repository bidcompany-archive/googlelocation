package bid.google.main;


import java.util.List;

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
	

}
