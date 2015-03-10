package com.vmware.radio14.worker;

import org.apache.log4j.Logger;

import com.vmware.radio14.client.Client;
import com.vmware.radio14.client.ClientManager;
import com.vmware.radio14.service.ServiceConstants;
import com.vmware.radio14.service.ServiceUtil;
import com.vmware.radio14.utils.PacketAddress;

/**
 * Worker Thread
 * 
 * @author admdev
 * 
 */
public class WorkerThread implements Runnable {
    private PacketAddress packetAddress;
    private static Logger logger = Logger.getLogger(WorkerThread.class.getName());

    /**
     * Constructor for WorkerThread class
     * 
     * @param address
     *            - PacketAddress instance
     * @param isSource
     */
    public WorkerThread(PacketAddress address) {
        packetAddress = address;
    }

    /**
     * Execute the job
     */
    public void run() {
        synchronized (logger) {

            Client client = null;
            String serviceName = null;
            String serverName = null;
            if (packetAddress.getSourceMatch() == true) {
                client =
                        ClientManager.getClient(packetAddress.getDestination(),
                                packetAddress.getDestinationPort());
                serviceName  = ServiceUtil.getServiceName(packetAddress.getSourcePort());
                serverName = packetAddress.getSource().getHostAddress();
            } else {
                client =
                        ClientManager.getClient(packetAddress.getSource(),
                                packetAddress.getSourcePort());
                serviceName = ServiceUtil.getServiceName(packetAddress.getDestinationPort());
                serverName = packetAddress.getDestination().getHostAddress();
            }

            if (client != null) {
                //print(packetAddress);
                System.out.println(client + " accessing service " + serviceName + " on Server " + serverName);
                
            }
        }

    }


    /**
     * Dummy method to print the contents of PacketAddress in synchronized way Note : For debugging
     * purpose only
     * 
     * @param packetAddress
     *            - {@link PacketAddress} instance to be printed
     */
    private void print(PacketAddress packetAddress) {
        logger.info("Thread Name = " + Thread.currentThread().getName());
        logger.info("      Source = "
                + ((packetAddress.getSource().getHostAddress() == null) ? "Unknown" : packetAddress
                        .getSource().getHostAddress()));
        logger.info("              Source Port = " + packetAddress.getSourcePort());
        logger.info("      Destination = "
                + ((packetAddress.getDestination() == null) ? "Unknown" : packetAddress
                        .getDestination().getHostAddress()));
        logger.info("              Desination Port = " + packetAddress.getDestinationPort());

        //TODO: Move the below code to a seperate method


    }
}
