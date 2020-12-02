package bid.csv.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bid.google.pojos.Location;
import bid.google.pojos.RawLocation;
import bid.google.props.Envs;



public class Csv {
	
	private static final Logger logger = LoggerFactory.getLogger(Csv.class);
	private Envs env;
	public Csv() {
		this.env = new Envs();
	}
	
	
	public List<RawLocation> readAddress() {
		List<RawLocation> ret = new ArrayList<RawLocation>();
		logger.debug("Reading: " + env.getFullpathcsv());
		logger.debug("Expecting: code at index [" + env.getIndexCode() +  "] and address at index [" + env.getIndexAddress() + "]");
		try {
			String row = "";
			BufferedReader csvReader = new BufferedReader(new FileReader(env.getFullpathcsv()));
			while ((row = csvReader.readLine()) != null) {
				RawLocation loc = new RawLocation();
				String[] data = row.split(env.getSeparator());
				loc.setCode(data[env.getIndexCode()]);
				loc.setAddress(data[env.getIndexAddress()]);
				ret.add(loc);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("Read complete.");
		
		return ret;
	}
	
	
	public void saveAddress(List<Location> places) {
		try {
			FileWriter output = new FileWriter(env.getFullpathoutput(), false);
			output.write(env.getOutputHeader() + "\n");
			for(Location loc : places) {
				output.write(loc.toString(env.getSeparator()) + "\n");
			}
			output.close();
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.debug("Saved complete");
	}
}
