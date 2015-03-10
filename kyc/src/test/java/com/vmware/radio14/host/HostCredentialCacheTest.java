package com.vmware.radio14.host;

import java.util.Map;

import junit.framework.TestCase;

/**
 * Unit Test for {@link HostCredentialCacheTest}
 * 
 * @author admdev
 * 
 */
public class HostCredentialCacheTest extends TestCase {

    /**
     * Constructor for {@link HostCredentialCacheTest}
     * 
     * @param name
     */
    public HostCredentialCacheTest(String name) {
        super(name);
    }

    /**
     * Test the Information loaded by the {@link HostCredentialCache}
     */
    public void testContent() {
        Map<String, Host> map = HostCredentialCache.getInstance().getMap();
        /*        for (Map.Entry<String, Host> entry: map.entrySet()) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                }*/
        assertEquals(2, map.size());
    }
}
