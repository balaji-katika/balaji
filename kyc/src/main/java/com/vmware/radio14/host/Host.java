package com.vmware.radio14.host;

/**
 * Represents a single Host and its related info
 * 
 * @author admdev
 * 
 */
public class Host {

    private String passwd;
    private String user;
    private String ip;

    /**
     * Constructor for Host
     * 
     * @param ip
     *            - IP Address
     * @param user
     *            - User Name
     * @param passwd
     *            - Password
     */
    public Host(String ip, String user, String passwd) {
        this.ip = ip;
        this.user = user;
        this.passwd = passwd;
    }

    /**
     * Returns the password
     * 
     * @return
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Returns the User Name
     * 
     * @return
     */

    public String getUser() {
        return user;
    }

    /**
     * Returns the IP Address
     * 
     * @return
     */
    public String getIp() {
        return ip;
    }
    
    @Override
    public String toString() {
        return "IP = " + ip + "; User = " + user + "; Password = " + passwd;
    }
}
