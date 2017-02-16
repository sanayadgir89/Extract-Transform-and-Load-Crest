package com.sana.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface FileWriter {
	
	
	/**
	 * This method is used to write the files
	 * @param targetDir
	 * @param file
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void writeFiles(File targetDir, final File file) throws IOException, FileNotFoundException;
	
	/**
	 * This method is used to write the files words summary
	 * @param targetDir
	 * @param words
	 * @param file
	 * @throws IOException
	 */
	public void writeWordsSummary(File targetDir, Map<String, Integer> words, String file) throws IOException ;

}
