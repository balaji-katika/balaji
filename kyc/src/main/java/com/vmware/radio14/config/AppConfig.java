/**
 * 
 */
package com.vmware.radio14.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author admdev
 * 
 */
public class AppConfig {
    private static final Logger logger = Logger.getLogger(AppConfig.class.getName());
    private static final String SCAN_PORTS = "scanPorts";
    public static final String FIXED_THREAD_SIZE = "fixedThreadSize";
    private static final String PRINT_SUMMARY = "printSummary";
    private static final String BOOL_TRUE = "true";
    private static AppConfig appConfig = new AppConfig();
    private boolean isLoaded = false;
    private Properties properties;
    private Set<Integer> scanPorts;

    /**
     * Private constructor
     */
    private AppConfig() {
        if (isLoaded == false) {
            load();
        }
        isLoaded = true;
    }

    /**
     * Loads the properties from the configuration file
     */
    public void load() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties");
        properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
         * Read interested ports
         */
        String scanPortsValue = properties.getProperty(SCAN_PORTS);
        logger.debug("Interested Ports = " + scanPortsValue);
        String ports[] = scanPortsValue.split(",");
        Integer intPorts[] = new Integer[ports.length];
        int i = 0;
        for (String port : ports) {
            intPorts[i++] = Integer.parseInt(port);
        }

        scanPorts = new HashSet<Integer>(Arrays.asList(intPorts));
    }

    /**
     * Returns the ports to be scanned
     * @return - Set of ports
     */
    public Set<Integer> getScanPorts() {
        return scanPorts;
    }

    /**
     * A static method to return the singleton instance
     * 
     * @return - singleton instance
     */
    public static AppConfig getInstance() {
        return appConfig;
    }

    /**
     * A method to refresh the properties on demand
     */
    public void refresh() {
        load();
    }

    /**
     * Read the property from the config file
     * 
     * @param property
     *            -
     * @return
     */
    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    /**
     * Returns true whether to generate the summary for the application
     * 
     * @return - true/false
     */
    public boolean printSummary() {
        String printSummary = null;
        printSummary = properties.getProperty(PRINT_SUMMARY);
        if (printSummary != null && printSummary.equalsIgnoreCase(BOOL_TRUE)) {
            return true;
        }

        return false;
    }

}
