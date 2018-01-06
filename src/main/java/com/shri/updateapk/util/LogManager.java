package com.shri.updateapk.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.Logger;

public class LogManager {

	private LogManager() {
		// Auto-generated constructor stub
	}

	public static void logException(Exception e, Logger logger) {
		StringWriter stack = new StringWriter();
		e.printStackTrace(new PrintWriter(stack));
		logger.error(stack);
	}
}
