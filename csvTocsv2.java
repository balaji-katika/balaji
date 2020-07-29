package com.walmartlabs.bkatika;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.strati.libs.commons.lang3.StringUtils;

public class csvTocsv2 {

	private static String NA = "NA";

	public static void main(String[] args) throws IOException {
		String csvFileIn = "/Users/bkatika/sunama/in1.csv";
		String line = "";
		String cvsSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileIn))) {
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] profile = line.split(cvsSplitBy);
				if (profile.length == 0) {
					break;
				}
				String gender = profile[1];
				if (gender.equalsIgnoreCase("Male")) {
					gender = "s/o";
				} else {
					gender = "d/o";
				}
				String gotram = profile[3];
				String qualification = profile[5];
				StringBuffer sb = new StringBuffer();
				String income = null;

				String email = "Not Available";
				if (profile.length >=11) {
					income = profile[10];	
				}
				if (profile.length >=12) {
					email = profile[11];	
				}
				
				if (StringUtils.isEmpty(income)) {
					income = "Not Available";
				}
				System.out.println(profile[0] + " " + gotram + " " + gender + " Shree " + profile[9] + " from "
				        + profile[7] + ". Completed " + qualification + " and working as " + profile[6] + " ("
				        + profile[4] + ")");
				sb.append("[{" + "\"na\":\"" + profile[0] + "\",");
				sb.append("\"ge\":\"" + profile[1] + "\",");
				sb.append("\"ag\":" + profile[2] + ",");
				sb.append("\"go\":\"" + profile[3] + "\",");
				sb.append("\"ph\":" + profile[4] + ",");
				sb.append("\"ed\":\"" + profile[5] + "\",");
				sb.append("\"oc\":\"" + profile[6] + "\",");
				sb.append("\"ad\":\"" + profile[7] + "\",");
				sb.append("\"pr\":\"" + profile[8] + "\",");
				sb.append("\"fa\":\"" + profile[9] + "\",");
				sb.append("\"in\":\"" + income + "\",");
				sb.append("\"em,\":\"" + email + "\"");
				sb.append("}]");
				//System.out.println(sb.toString());
				invokePUTtoFirebase(sb.toString(), profile[4]);
			}
		}
		catch (IOException ioException) {
			System.err.println(ioException);
		}

	}

	private static void invokePUTtoFirebase(String input, String phone) {
		HttpURLConnection conn = null;
		try {

			URL url = new URL("https://sunama-jakini.firebaseio.com/profiles/0/" + phone + ".json");
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
			}
			br.close();

			conn.disconnect();
		}
		catch (Exception e) {
			System.err.println(e);
		}
		finally {
			if (conn != null) {
				conn.disconnect();
			}

		}
	}
}
