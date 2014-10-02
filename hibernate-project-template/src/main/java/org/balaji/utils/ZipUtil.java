package org.balaji.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.balaji.exception.BalajiException;

/**
 * Utility for compress/uncompress zip files
 * 
 * @author root
 *
 */
public class ZipUtil {
	private static Logger logger = Logger.getLogger(ZipUtil.class);

	public static void unzip(String zipFile, String outputFolder)
			throws BalajiException {
		byte[] buffer = new byte[1024];
		try {
			// create output directory if not exist
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// Get the zip file content
			ZipInputStream zipInputStream = new ZipInputStream(
					new FileInputStream(zipFile));

			// Get the zip file
			ZipEntry entry = zipInputStream.getNextEntry();
			while (entry != null) {
				String fileName = entry.getName();
				File newFile = new File(outputFolder + File.separator
						+ fileName);
				if (entry.isDirectory()) {
					logger.debug("Creating directory " + fileName);
					newFile.mkdirs();
					entry = zipInputStream.getNextEntry();
					continue;
				}

				logger.debug("File unzipped : " + newFile.getAbsolutePath());

				// Create the required non existent folders
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len = 0;
				while ((len = zipInputStream.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				entry = zipInputStream.getNextEntry();
			}

			/* TODO: check if this needs to be done before */
			zipInputStream.closeEntry();
			zipInputStream.close();
			logger.info("Successfully unzipped file " + zipFile);

		} catch (FileNotFoundException e) {
			logger.error("File " + zipFile + "  not found", e);			
			throw new BalajiException("File " + zipFile + " not found");
		} catch (IOException e) {
			logger.error("Error reading zip file " + zipFile, e);						
			throw new BalajiException("Error reading zip file " + zipFile);
		}
	}
}
