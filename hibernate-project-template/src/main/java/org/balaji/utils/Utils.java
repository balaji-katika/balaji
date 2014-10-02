package org.balaji.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.balaji.exception.BalajiException;

/**
 * Utility class
 * 
 * @author root
 *
 */
public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);

	private static final int HTTP_OK = 200;

	public static String httpGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() != HTTP_OK) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

	public static File httpGetFile(String urlStr) {

		/*
		 * Make sure working directory location is mentioned in the
		 * configuration
		 */
		String workingDir = System
				.getProperty(SysPropertiesMap.PROP_WORKING_DIR);
		if (workingDir == null) {
			logger.error("Invalid Usage");
			logger.error("Parameter : workingDir is missing in configuration file");
			return null;
		}
		/*
		 * Check for the existence of the working directory. If not create it.
		 */
		File file = new File(workingDir);
		if (!(file.exists())) {
			logger.debug("Working Directory " + workingDir
					+ " does not exist. Creating...");
			if (!file.mkdirs()) {
				logger.error("Unable to create working directory... Exiting...");
				return null;
			}
		}

		/*
		 * Create the filename from the URL string
		 */
		String fileName = parseFileName(urlStr);
		logger.debug("File Name = " + fileName);

		File zipFile = new File(workingDir + File.separator + fileName + ".zip");
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			logger.error("MalformedURL supplied " + urlStr, e);
			return null;
		}

		logger.debug("Creating file path" + file.getPath());
		logger.debug("Absolute path " + file.getAbsolutePath());

		/*
		 * Initiate the http connection
		 */
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			if (connection.getResponseCode() != HTTP_OK) {
				logger.error("Recieved response code "
						+ connection.getResponseCode()
						+ "; Response Message = "
						+ connection.getResponseMessage());
				return null;
			}
		} catch (IOException e) {
			logger.error("Error connecting to " + urlStr, e);
			return null;
		}

		/*
		 * Read the stream from the connection
		 */
		byte[] b = new byte[1024];
		int len = -1;
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(
					connection.getInputStream());
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			while ((len = bufferedInputStream.read(b)) != -1) {
				fileOutputStream.write(b, 0, len);
				logger.debug("Written " + b.length
						+ " bytes to the output file");
			}
			/*
			 * Flush and close the fileoutput stream
			 */
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			logger.error("Error getting input stream ", e);
			return null;
		}

		/*
		 * Disconnect the http connection
		 */
		connection.disconnect();
		return zipFile;
	}

	/**
	 * Parse the file name from the URL. URL format :
	 * www.url.com/arg0/arg1/../argn?filename=<name>
	 * 
	 * @param urlStr
	 * @return
	 */
	private static String parseFileName(String urlStr) {
		String[] str = urlStr.split("filename=");
		return str[1];
	}

	/**
	 * Load properties from the file path specified
	 * 
	 * @param fileLocation
	 * @return
	 * @throws BalajiException
	 */
	public static Properties loadPropertiesFromFile(String fileLocation)
			throws BalajiException {
		FileInputStream fileInputStream = null;
		Properties p = new Properties();
		try {
			fileInputStream = new FileInputStream(fileLocation);
			p.load(fileInputStream);
		} catch (FileNotFoundException e) {
			logger.error("File " + fileLocation + " not found", e);
			throw new BalajiException("File " + fileLocation + " not found");
		} catch (IOException e) {
			logger.error("Error reading cache file " + fileLocation, e);
			throw new BalajiException("Error reading cache file "
					+ fileLocation);
		}

		return p;
	}

}
