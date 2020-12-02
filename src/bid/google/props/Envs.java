package bid.google.props;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Envs {
	
	private static final Logger logger = LoggerFactory.getLogger(Envs.class);
	
	private Properties props;
	private final String propPath = "./props.properties";
	private String apikey;
	private String fullpathcsv;
	private String outputHeader;
	private int indexCode;
	private int indexAddress;
	private String apiep;
	private int wait; 
	private String fullpathoutput;
	private String separator; 
	
	
	public Envs() {
		props = new Properties();
		this.apikey = System.getenv("GOOGLE_GEOCODE_APIKEY") ;
		if(apikey == null) {
			try {
				props.load(new FileInputStream(propPath));
				
				apikey = props.getProperty("apikey");
				apiep = props.getProperty("apiep");
				fullpathcsv = props.getProperty("fullpathcsv");
				fullpathoutput = props.getProperty("fullpathoutput");
				indexCode = Integer.parseInt(props.getProperty("indexcode"));
				indexAddress = Integer.parseInt(props.getProperty("indexaddress"));
				outputHeader = props.getProperty("outputHeader");
				wait = Integer.parseInt(props.getProperty("wait"));
				separator = props.getProperty("separator");
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getFullpathoutput() {
		return fullpathoutput;
	}

	public void setFullpathoutput(String fullpathoutput) {
		this.fullpathoutput = fullpathoutput;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public String getApiep() {
		return apiep;
	}

	public void setApiep(String apiep) {
		this.apiep = apiep;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public int getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(int indexCode) {
		this.indexCode = indexCode;
	}

	public int getIndexAddress() {
		return indexAddress;
	}

	public void setIndexAddress(int indexAddress) {
		this.indexAddress = indexAddress;
	}

	public String getOutputHeader() {
		return outputHeader;
	}

	public void setOutputHeader(String outputHeader) {
		this.outputHeader = outputHeader;
	}

	public String getFullpathcsv() {
		return fullpathcsv;
	}

	public void setFullpathcsv(String fullpathcsv) {
		this.fullpathcsv = fullpathcsv;
	}

	
	

}
