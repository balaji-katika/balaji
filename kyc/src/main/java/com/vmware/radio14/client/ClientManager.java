package com.vmware.radio14.client;

import java.net.InetAddress;

import org.apache.log4j.Logger;

import com.vmware.radio14.host.Host;
import com.vmware.radio14.host.HostCredentialCache;
import com.vmware.radio14.os.CommandPrompt;
import com.vmware.radio14.os.CommandPromptFactory;
import com.vmware.radio14.os.Result;

/**
 * Client Manager responsible for loading the client based on the IP Packet
 * 
 * @author admdev
 * 
 */
public class ClientManager {

    private static final String CMD_PID = "netstat -anp";
    private static Logger logger = Logger.getLogger(ClientManager.class.getName());

    /**
     * Fetches the {@link Client} information based on the {@link InetAddress}
     * 
     * @param address
     *            - InetAddress on which to probe the {@link Client}
     * @param port
     *            - Dynamic port
     * @return - {@link Client} information
     */
    public static Client getClient(InetAddress address, int port) {
        Client client = null;
        Result result = null;

        Host host = HostCredentialCache.getInstance().getHost(address.getHostAddress());
        if (host == null) {
            logger.warn("Skipping client " + address.getHostAddress()
                    + " due to lack of credentials");
            ;
            return client;
        }

        client = new Client(address);

        CommandPrompt commandPrompt =
                CommandPromptFactory.getCommandPrompt(CommandPrompt.osType.LINUX);

        /*
         * Get the process ID associated with the port
         */
        StringBuffer pid_sb = new StringBuffer();
        pid_sb.append("netstat -anp | grep ");
        pid_sb.append(port);
        pid_sb.append(" | awk '{print $7}' | cut -d'/' -f1");
        result =
                commandPrompt.execute(host.getIp(), host.getUser(), host.getPasswd(),
                        pid_sb.toString());
        logger.debug("PID Result = " + result);
        /*
         * Get the Process information
         */

        StringBuffer pinfo_sb = new StringBuffer();
        pinfo_sb.append("ps -ww -A -o \"pid,cmd\" | grep ");
        pinfo_sb.append(result.getOutput());
        result =
                commandPrompt.execute(host.getIp(), host.getUser(), host.getPasswd(),
                        pinfo_sb.toString());

        
        /**
         * Verify jboss AS software
         */
        if (result.getOutput().contains("org.jboss.Main")) {
            client.setAppName(ClientAppConstants.JBOSS_AS);
           
        }
        else if (result.getOutput().contains("VNC-Viewer")) {
            client.setAppName(ClientAppConstants.VNC);
        }


        return client;
    }

}
