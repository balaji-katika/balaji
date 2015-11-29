package org.balaji.programming.topK;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Test {

	private static final int TOP_N = 5;

	public static void main(String[] args) {
		Map<String, Element> histoMap = parseInFile();
		//System.out.println(histoMap);
	}

	private static Map<String, Element> parseInFile() {
		Map<String, Element> histo = new HashMap<String, Element>();
		Element wordCount = null;
		ScoreCard scoreCard = new ScoreCard(TOP_N, histo);

		try (BufferedReader br = new BufferedReader(new FileReader(new File("/Users/bkatika/temp/in.txt")));){
			String word = null;
			while ((word = br.readLine()) != null) {
				wordCount = null;
				wordCount = histo.get(word);
				if(wordCount != null) 
				{
					wordCount.increment();
				}
				else {					
					wordCount = new Element(word, 1, -1);					
				}
				scoreCard.refresh(wordCount);
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(scoreCard);
		return histo;
	}

}
