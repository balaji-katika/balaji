package com.vmware.radio14.os;

/**
 * Class representing the Result of the Command
 * 
 * @author admdev
 * 
 */
public class Result {
    public static final int ERR_RET_CODE = -1;
    public static final String ERR_OUT_MSG = "Error";
    int retCode;
    String output;

    /**
     * Constructor for the {@link Result}
     * 
     * @param retCode
     * @param output
     */
    public Result(int retCode, String output) {
        this.retCode = retCode;
        this.output = output;
    }

    public Result() {
        this.output = ERR_OUT_MSG;
        this.retCode = ERR_RET_CODE;
    }

    /**
     * Return code indicating the success/failure of the command
     * 
     * @return - int representing the return code
     */
    public int getRetCode() {
        return retCode;
    }

    /**
     * Output of the command
     * 
     * @return - String
     */
    public String getOutput() {
        return output;
    }

    /**
     * Setter for Return Code
     * 
     * @param exitStatus
     */
    public void setRetCode(int exitStatus) {
        this.retCode = exitStatus;

    }

    /**
     * Setter for Command Output
     * 
     * @param resultOutputString
     */
    public void setOutput(String resultOutputString) {
        this.output = resultOutputString;
    }
    
    @Override
    public String toString() {       
        return "Return Code = " + retCode + "; Output = " + output;
    }


}
