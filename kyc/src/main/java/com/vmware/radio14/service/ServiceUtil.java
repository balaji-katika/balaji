package com.vmware.radio14.service;

/**
 * Utility for Service related activities
 * @author admdev
 *
 */
public class ServiceUtil {

    /**
     * Get the service name associated with the port
     * @param port - Port Number
     * @return
     */
    public static String getServiceName(int port) {
        if (port == 5432) {
            return ServiceConstants.POSTGRESQL;
        }
        if (port == 1521) {
            return ServiceConstants.ORACLE;
        }
        return null;
    }

}
