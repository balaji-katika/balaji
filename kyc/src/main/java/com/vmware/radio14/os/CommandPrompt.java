package com.vmware.radio14.os;


/**
 * Interface for executing commands with the Host
 * 
 * @author admdev
 *
 */
public interface CommandPrompt {

    /**
     * OS Type
     * @author admdev
     *
     */
    public static enum osType {
        WINDOWS,
        LINUX                
    }
    
    /**
     * Execute the specified command against the host with the supplied credentials
     * 
     * @param host
     * @param user
     * @param pwd
     * @param command
     * @return
     */
    public Result execute(String host, String user, String pwd, String command);

}
