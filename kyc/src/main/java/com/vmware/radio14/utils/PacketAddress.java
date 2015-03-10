package com.vmware.radio14.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import com.vmware.radio14.config.AppConfig;

/**
 * Utility class for Holding IP v4 address
 * 
 * @author admdev
 * 
 */
public class PacketAddress {
    private static Logger loger = Logger.getLogger(PacketAddress.class.getName());

    private InetAddress source;

    private InetAddress destination;

    private int sourcePort;

    private int destinationPort;

    private boolean isSourceMatch = false;

    /**
     * Constructor for Ip4AddressUtil
     * 
     * @param ip4Header
     *            Ip4 header information
     * @param tcpHeader
     *            TCP header information
     */
    public PacketAddress(Ip4 ip4Header, Tcp tcpHeader) {
        byte[] ba = null;
        /*
         * Parse source IP address
         */
        ba = ip4Header.source();
        try {
            source = Inet4Address.getByAddress(ba);
        } catch (UnknownHostException e) {
            loger.error("Error Parsing for source IP " + ba);
            e.printStackTrace();
        }

        /*
         * Parse destination IP address
         */
        ba = ip4Header.destination();
        try {
            destination = Inet4Address.getByAddress(ba);
        } catch (UnknownHostException e) {
            loger.error("Error Parsing for Destination IP " + ba);
            e.printStackTrace();
        }

        /*
         * Parse the TCP source/destination port 
         */
        sourcePort = tcpHeader.source();
        destinationPort = tcpHeader.destination();
    }

    /**
     * 
     * @return Source InetAddress
     */
    public InetAddress getSource() {
        return source;
    }

    /**
     * 
     * @return Destination InetAddress
     */
    public InetAddress getDestination() {
        return destination;
    }

    /**
     * Get the Source Port number
     * 
     * @return - Port number
     */
    public int getSourcePort() {
        return sourcePort;
    }

    /**
     * Get the destination port number
     * 
     * @return - port number
     */
    public int getDestinationPort() {
        return destinationPort;
    }

    /**
     * Returns true if the {@link PacketAddress} matches the ports to be scanned
     * 
     * @param isSource
     *            - Boolean shall be filled with true if Source Port Matches. value will be false if
     *            destinationPort matches (Note: this Boolean value is valid only when the method
     *            returns true)
     * 
     * @return - true if the PacketAddress has ports to be scanned (specified by the user in the
     *         config file). false otherwise.
     */
    public boolean hasScanPorts() {
        if (AppConfig.getInstance().getScanPorts().contains(new Integer(sourcePort))) {
            isSourceMatch  = true;
            return true;
        }

        if (AppConfig.getInstance().getScanPorts().contains(new Integer(destinationPort))) {
            isSourceMatch = false;
            return true;
        }
        return false;
    }
    
    /**
     * Returns if the source has matched
     * @return
     */
    public boolean getSourceMatch() {
        return isSourceMatch;
    }
}
