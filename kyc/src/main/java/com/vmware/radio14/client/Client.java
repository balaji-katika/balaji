package com.vmware.radio14.client;

import java.net.InetAddress;

/**
 * Class representing the Client Application
 * @author admdev
 *
 */
public class Client {
    private InetAddress address;
    private String applicationName;

    /**
     * Constructor for the {@link Client}
     * @param address
     */
    public Client(InetAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {        
        return "Client " + address.getHostAddress() + " has " + applicationName;
    }

    /**
     * Setter for Client Aplication Name
     * @param applicationName
     */
    public void setAppName(String applicationName) {
        this.applicationName = applicationName;        
    }

}
