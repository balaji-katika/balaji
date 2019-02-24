package com.walmartlabs.bkatika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class csvTocsv {

    public static void main(String[] args) throws IOException {
        String csvFileIn = "/Users/bkatika/in.csv";
        String csvFileOut = "/Users/bkatika/out.csv";
        String line = "";
        String cvsSplitBy = ",";
        BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileOut));
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileIn))) {
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] profile = line.split(cvsSplitBy);
                StringBuffer sb = new StringBuffer();
                String email = profile[7];
                String gender = profile[3];
                if (gender.equalsIgnoreCase("Male")) {
                    gender = "s/o";
                } else {
                    gender = "d/o";
                }
                String gotram = profile[5];
                String qualification = profile[8];
                String prefs;
                if (profile.length == 13) {
                    prefs = profile[12];
                }
                else {
                    prefs = " ";
                }
                System.out.println(profile[1] + " " + gotram + " "
                        + gender + " Shree " + profile[2] + " from "
                        + profile[12] + ". Completed " + qualification
                        + " and working as " + profile[9] + " (" + profile[6] + ")");
                sb.append(profile[0] + cvsSplitBy + profile[1] + cvsSplitBy
                        + profile[3] + cvsSplitBy + profile[4] + cvsSplitBy
                        + profile[5] + cvsSplitBy + profile[6] + cvsSplitBy
                        + profile[8] + cvsSplitBy + profile[9] + cvsSplitBy
                        + profile[12] + cvsSplitBy + prefs + cvsSplitBy
                        + profile[2] + cvsSplitBy + " " + cvsSplitBy
                        + ((email.length() == 0) ? "NA" : email));
                bw.write(sb.toString() + "\n");

            }
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
        finally {
            bw.close();
        }

    }

}
