package com.sana.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface FileService {

	/**
	 * List all the files under a directory
	 * 
	 * @param wordSumDir
	 * @param capContentDir
	 * @param directoryName
	 *            to be listed
	 * @throws IOException
	 */
	public void listFiles(File sourceDir, File capContentDir, File wordSumDir) throws IOException
			;

	/**
	 * 
	 * @param capContentDir
	 * @param fileEntry
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void capitalizeContent(File capContentDir, final File fileEntry) throws IOException
			;

	/**
	 * 
	 * @param wordSumDir
	 * @param wordsAndCount
	 * @param fileName
	 * @throws IOException
	 */
	public void writeSummary(File wordSumDir,
			Map<String, Integer> wordsAndCount, String fileName)
			throws IOException;

	/**
	 * 
	 * @param uniquewords
	 * @param out
	 * @throws IOException
	 */
	public void writeUniqueWords(Map<String, Integer> uniquewords,
			BufferedWriter out) throws IOException;

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public Map<String, Integer> getWordsAndCount(String path)
			throws IOException;
}
