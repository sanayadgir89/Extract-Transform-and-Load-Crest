package com.sana.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

	/**
	 * List all the files under a directory
	 * 
	 * @param wordSumDir
	 * @param capContentDir
	 * @param uniqueWordsDir 
	 * @param directoryName
	 *            to be listed
	 * @throws IOException
	 */
	public void listFiles(File sourceDir, File capContentDir, File wordSumDir, File uniqueWordsDir)
			throws IOException;

	/**
	 * 
	 * @param capContentDir
	 * @param fileEntry
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void capitalizeContent(File capContentDir, final File fileEntry)
			throws IOException;

	/**
	 * This method is used to populate the file word summary.
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void populateWordsCount(String path) throws IOException;

}
