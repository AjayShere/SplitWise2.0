package com.split.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerExt {

	
	public static Logger getLogger(String logg) {
		 Logger log = LoggerFactory.getLogger(logg);
		return log;
	}
	
	
	
	public void debug(String ClassName , String msg) {
		
		getLogger("debug").debug(logString(ClassName , msg));
	}
	
	
	public static String logString(String className , String msg) {
		return className + " " + msg; 
	}
	
	
}
