package com.vmware.radio14.os;

import com.vmware.radio14.os.CommandPrompt.osType;

/**
 * Factory to create Command prompt based on the OS Type
 * @author admdev
 *
 */
public class CommandPromptFactory {

    /**
     * Returns the instance of the {@link CommandPrompt} for the given OS Type
     * @param os
     * @return
     */
    public static CommandPrompt getCommandPrompt(osType os) {
        
        if (os == osType.LINUX) {
            return new LinuxCommandPrompt();
        }
        return null;
    }

}
