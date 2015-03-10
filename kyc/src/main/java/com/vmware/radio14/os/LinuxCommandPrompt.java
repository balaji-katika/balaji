package com.vmware.radio14.os;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * Class implementing the {@link CommandPrompt} for Linux OS
 * 
 * @author admdev
 * 
 */
public class LinuxCommandPrompt implements CommandPrompt {
    //private static final Logger logger = Logger.getLogger(LinuxCommandPrompt.class.getName());
    private static final int SSH_PORT = 22;

    public class KYCUserInfo implements UserInfo {

        public String getPassphrase() {
            // TODO Auto-generated method stub
            return null;
        }

        public String getPassword() {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean promptPassphrase(String arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean promptPassword(String arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean promptYesNo(String arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        public void showMessage(String arg0) {
            // TODO Auto-generated method stub

        }

    }


    public Result execute(String host, String user, String pwd, String command) {
        Result result = new Result();
        StringBuffer sb = new StringBuffer();
        ByteArrayOutputStream errStream = null;


        Session session = null;
        JSch jSch = new JSch();
        try {
            /*
             * Create session
             */
            session = jSch.getSession(user, host, SSH_PORT);
            UserInfo userInfo = new KYCUserInfo();
            session.setUserInfo(userInfo);

            //TODO: Strict Host Key Checking disabled temporarily to avoid additional key authentication
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(pwd);

            session.connect();

            /*
             * Open an exec channel 
             */
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);

            errStream = new ByteArrayOutputStream();
            ((ChannelExec) channel).setErrStream(errStream);

            InputStream in = channel.getInputStream();
            channel.connect();

            /*
             * Read the command output 
             */
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    sb.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    result.setRetCode(channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();


        } catch (JSchException e) {
            result.setRetCode(Result.ERR_RET_CODE);
            result.setOutput(Result.ERR_OUT_MSG);
            e.printStackTrace();
        } catch (IOException e) {
            result.setRetCode(Result.ERR_RET_CODE);
            result.setOutput(Result.ERR_OUT_MSG);
            e.printStackTrace();
        }
        String cmdOutPut = sb.toString();
        String resultOutputString = cmdOutPut.trim().isEmpty() ? errStream.toString() : cmdOutPut;
        result.setOutput(resultOutputString);

        return result;
    }

}
