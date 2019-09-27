package Utlities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class URL {

	private static Logger log = LogManager.getLogger(Utlities.URL.class.getName());
	public static final String URL = "http://localhost:8091";
	
	public static String getEndPoint(){
		log.info("Base URI : " + URL);
		return URL;
	}
	
	public static String getEndPoint(String resource){
		log.info("URI End Point : " + URL + resource);
		return URL + resource;
	}	
}
