package org.balaji.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.balaji.exception.BalajiException;
import org.balaji.utils.SysConstants;
import org.balaji.utils.SysPropertiesMap;

/**
 * Singleton class to hold product specific configuration
 * 
 * @author root
 *
 */
public class ProductConfig {
	private static Logger logger = Logger.getLogger(ProductConfig.class);
	private static ProductConfig config = null;
	private Map<String, String> propertyMap;

	/**
	 * private constructor
	 * 
	 * @param productConfig
	 */
	private ProductConfig(String productConfig) {
		propertyMap = new HashMap<String, String>();
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			inputStream = new FileInputStream(productConfig);
			properties.load(inputStream);
			for (String key : properties.stringPropertyNames()) {
				propertyMap.put(key, properties.getProperty(key));
			}
			logger.debug("Product Config Properties = " + properties.toString());

		} catch (FileNotFoundException e) {
			logger.error("Error reading Product Config file" + productConfig);
			e.printStackTrace();
			System.exit(-6);
		} catch (IOException e) {
			logger.error("Exception occured", e);
			e.printStackTrace();
			System.exit(-7);
		}	
	}

	/**
	 * @param productConfig
	 * @throws BalajiException
	 * 
	 */
	public static void init(String productConfig) throws BalajiException {
		/**
		 * Throw an exception if the product configuration is not supplied or
		 * the file is missing
		 */
		if (productConfig == null || !(new File(productConfig)).exists()) {
			throw new BalajiException("Product Configuration file missing");
		}

		if (config == null) {
			config = new ProductConfig(productConfig);
		}
	}

	/**
	 * Get the static instance associated with the singleton
	 * 
	 * @return
	 */
	public static ProductConfig getInstance() {
		return config;
	}

	/**
	 * Get the value for the property
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return propertyMap.get(key);
	}

	/**
	 * Get the value for the property. If property does not exist, return the
	 * default value
	 * 
	 * @param key
	 *            - Property Name
	 * @param defVal
	 *            - Default Value to be returned if property not present
	 * 
	 * @return - Value associated with the property
	 */
	public String getProperty(String key, String defVal) {
		String val = propertyMap.get(key);
		if (val == null) {
			return defVal;
		} else {
			return val;
		}
	}

}
