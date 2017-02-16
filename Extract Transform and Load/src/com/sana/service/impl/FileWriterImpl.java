package com.sana.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FileWriterImpl implements com.sana.service.FileWriter {

	/**
	 * This method is used to write the files
	 * @param targetDir
	 * @param file
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void writeFiles(File targetDir, File file) throws IOException,
			FileNotFoundException {
		File outputFile = new File(targetDir, file.getName());
		BufferedReader in = null;
		PrintWriter out = null;
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		in = new BufferedReader(new FileReader(file));
		out = (new PrintWriter(new FileWriter(outputFile)));
		int ch;
		while ((ch = in.read()) != -1) {
			ch = Character.toUpperCase(ch);
			out.write(ch);
		}

		in.close();
		out.close();
	}

	/**
	 * This method is used to write the files words summary
	 * @param targetDir
	 * @param words
	 * @param file
	 * @throws IOException
	 */
	 public void writeWordsSummary(File targetDir,
			Map<String, Integer> words, String file) throws IOException {
		 File tagetFile = new File(targetDir , file);
         if (!tagetFile.exists()) {
        	 tagetFile.createNewFile();
         }
        BufferedWriter  out = new BufferedWriter(new FileWriter(tagetFile.getPath()));
 	    Iterator<Entry<String, Integer>> it = words.entrySet().iterator();
 	   out.write("WORD												COUNT\n\n");
 	   out.write("------------------------------------------------------------------\n");
 	    while (it.hasNext()) {
 	        Entry<String, Integer> pairs = it.next();
 	        out.write(pairs.getKey() + "												" + pairs.getValue() + "\n");
 	    }
 	    out.close();
		
	}

}
