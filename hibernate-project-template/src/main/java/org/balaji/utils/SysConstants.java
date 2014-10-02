package org.balaji.utils;

import java.io.File;

/**
 * Class to hold System Constants
 * 
 * @author root
 *
 */
public class SysConstants {

	private static final String CONST_USER_HOME = System
			.getProperty("user.home");

	public static final String CONST_DEF_LOG_CONF_LOCATION = System
			.getProperty(CONST_USER_HOME) + File.separator + "log4j.properties";

	/**
	 * Get the default class name for bean locations
	 * 
	 * @return
	 */
	public static String getBeanLocation() {
		return "spring/config/BeanLocations.xml";
	}
}
