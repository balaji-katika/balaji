package com.vmware.radio14.os;

import java.util.Iterator;

import com.vmware.radio14.host.Host;
import com.vmware.radio14.host.HostCredentialCache;

import junit.framework.TestCase;

public class LinuxCommandPromptTest extends TestCase {

    private static final String TEST_CMD = "whoami";

    public LinuxCommandPromptTest(String name) {
        super(name);
    }
    
    /**
     * Test the working of the LinuxCommandPrompt
     */
    public void testFunctionality() {
        
         /*Iterator<Host> iter = HostCredentialCache.getInstance().getMap().values().iterator();
         Host host = iter.next();
         CommandPrompt commandPrompt = CommandPromptFactory.getCommandPrompt(CommandPrompt.osType.LINUX);  
         Result result = commandPrompt.execute(host.getIp(), host.getUser(), host.getPasswd(), TEST_CMD);
         assertEquals(0, result.getRetCode());*/
    }

}
