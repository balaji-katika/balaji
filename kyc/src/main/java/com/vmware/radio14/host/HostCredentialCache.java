package com.vmware.radio14.host;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Cache for Host Credentials
 * 
 * @author admdev
 * 
 */
public class HostCredentialCache {
    private static final String HOSTS_XML_FILE = "hosts.xml";
    private static final String USER_HOME = "user.home";
    private static final String ELEMENT_HOST = "host";
    private static final String ELEMENT_IP = "ip";
    private static final String ELEMENT_USER = "user";
    private static final String ELEMENT_PASSWD = "passwd";
    private static Map<String, Host> hostCredentialMap;
    private static HostCredentialCache instance = new HostCredentialCache();
    

    /**
     * Constructor for {@link HostCredentialCache}
     */
    private HostCredentialCache() {
        hostCredentialMap = new HashMap<String, Host>();
        load();
    }

    private void load() {
        File hostsFile = new File(new File(System.getProperty(USER_HOME)), HOSTS_XML_FILE);
        DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory.newInstance();
        Host host = null;
        try {
            DocumentBuilder dBuilder = dbBuilderFactory.newDocumentBuilder();
            Document document = dBuilder.parse(hostsFile);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName(ELEMENT_HOST);

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String ip = element.getElementsByTagName(ELEMENT_IP).item(0).getTextContent();
                    host =
                            new Host(ip, element.getElementsByTagName(ELEMENT_USER).item(0)
                                    .getTextContent(), element.getElementsByTagName(ELEMENT_PASSWD)
                                    .item(0).getTextContent());
                    hostCredentialMap.put(ip, host);
                }
            }

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Returns the static instance for the {@link HostCredentialCache}
     * 
     * @return
     */
    public static HostCredentialCache getInstance() {
        return instance;
    }

    /**
     * Returns the host associated with the IP Address
     * 
     * @param ip
     *            - IP Address in a.b.c.d format
     * @return - Host instance
     */
    public Host getHost(String ip) {
        return hostCredentialMap.get(ip);
    }
    
    /**
     * Returns the map object.
     * Used for testing only
     * 
     * @return - Map instance
     */
    public Map<String, Host> getMap()
    {
        return hostCredentialMap;
    }

}
