package com.selenium.utility;

import org.apache.logging.log4j.*;

public class Log {
	
	public static Logger log = LogManager.getLogger(Log.class.getName());
		
	
	public static void startTest(String testName) {
		log.info("---------------------- Test " + testName + " started ---------------------");
	}
	
	public static void endTest(String testName) {
		log.info("---------------------- Test " + testName + " ended ---------------------");
	}
	
	public static void info(String message) {
		log.info(message);
	}
	
	public static void warn(String message) {
		log.warn(message);
	}
	
	public static void error(String message) {
		log.error(message);
	}
	
	public static void debug(String message) {
		log.debug(message);
	}
	
	
	
	

}
