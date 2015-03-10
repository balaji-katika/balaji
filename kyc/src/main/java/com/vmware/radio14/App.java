package com.vmware.radio14;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.*;
import org.jnetpcap.*;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import com.vmware.radio14.config.AppConfig;
import com.vmware.radio14.utils.PacketAddress;
import com.vmware.radio14.worker.WorkerThread;

/**
 * Class of kyc application
 * 
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());
    protected int packetsCount = 0;
    private static ExecutorService executor;
    private Pcap pcap = null;

    /**
     * Application entry point
     * 
     * @param args
     */
    public static void main(String[] args) {
        App app = new App();

        app.init();

        app.process();

        app.shutdown();

        app.printSummary();
    }

    /**
     * Generate Summary
     */
    private void printSummary() {
        if (AppConfig.getInstance().printSummary()) {
            logger.info("Total Packets Parsed = " + packetsCount);
        }
    }

    /**
     * Main processing done here
     */
    private void process() {
        /*
         * Create a packet handler
         */
        PcapPacketHandler<String> jPacketHandler = new PcapPacketHandler<String>() {
            public void nextPacket(PcapPacket packet, String user) {
                PacketAddress packetAddress = null;
                /*
                 * Parse the packet header Information
                 */
                Ip4 ip4 = new Ip4();
                Tcp tcp = new Tcp();
                if (packet.hasHeader(ip4) && packet.hasHeader(tcp)) {
                    //This is a IPv4 & TCP packet
                    packetAddress = new PacketAddress(ip4, tcp);
                }

                if (packetAddress == null || !packetAddress.hasScanPorts()) {
                    /*
                     * Do not process packet if it does not match the list of scanned ports 
                     */
                    return;
                }

                Runnable workerThread = new WorkerThread(packetAddress);
                executor.execute(workerThread);
                packetsCount++;
            }
        };

        /*
         * Loop through the pcap 10 times as root user 
         */
        pcap.loop(100, jPacketHandler, "root");
    }

    /**
     * Initialize the application
     */
    private void init() {

        /*
         * Read the configured properties
         */
        int deviceNumber = Integer.parseInt(AppConfig.getInstance().getProperty("deviceNumber"));
        int snaplen = Integer.parseInt(AppConfig.getInstance().getProperty("snapLen"));
        int flags = Pcap.MODE_PROMISCUOUS;
        int timeout = Integer.parseInt(AppConfig.getInstance().getProperty("timeOut"));

        /*
         * Read all the network interfaces
         */
        List<PcapIf> alldevs = new ArrayList<PcapIf>();
        StringBuilder errbuf = new StringBuilder();

        int r = Pcap.findAllDevs(alldevs, errbuf);
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
            logger.error("Can't read list of devices error is " + errbuf.toString());
            return;
        }

        /*
         * Open the selected device (can be configured through config.properties file)
         */

        PcapIf device = alldevs.get(deviceNumber);


        pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
        if (pcap == null) {
            logger.error("Error while opening device for capture : " + errbuf.toString());
            //TODO: Exit the application here
            shutdown();
            System.exit(-1);
        }

        /*
         * Initialize the executor framework
         */
        int fixedThreadPoolSize =
                Integer.parseInt(AppConfig.getInstance().getProperty(AppConfig.FIXED_THREAD_SIZE));
        executor = Executors.newFixedThreadPool(fixedThreadPoolSize);

        /*
         * Add a Signal handler for Ctrl+C
         */
        /*
         * handleCtrlC()
         * {
         * if (pcap !=null) {
         *      pcap.close();
         * }
         */
    }

    /**
     * Shutdown the application
     */
    private void shutdown() {
        /*
         * Close jNetPcap 
         */
        if (pcap != null) {
            pcap.close();
            pcap = null;
        }

        /*
         * Shutdown the executor
         */
        if (executor != null) {
            executor.shutdown();
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
