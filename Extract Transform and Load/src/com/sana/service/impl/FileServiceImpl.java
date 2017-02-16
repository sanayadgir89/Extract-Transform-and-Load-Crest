package com.sana.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import com.sana.service.FileService;
import com.sana.service.FileWriter;

/**
 *
 * @author Sana Yadgir
 */
public class FileServiceImpl implements FileService {

	FileWriter fileWriter = new FileWriterImpl();

	Map<String, Integer> words = new HashMap<String, Integer>();

	Map<String, Integer> uniqueWords = new HashMap<String, Integer>();

	/**
	 * List all the files under a directory
	 * 
	 * @param wordSumDir
	 * @param capContentDir
	 * @param directoryName
	 *            to be listed
	 * @throws IOException
	 */
	public void listFiles(final File sourceDir, File capContentDir,
			File wordsDir, File uniqueWordsDir) throws IOException {

		System.out.println(" Directory Files");
		if (sourceDir != null) {
			for (final File fileEntry : sourceDir.listFiles()) {
				System.out.println("FILE NAME  :" + fileEntry);
				resetMap();
				capitalizeContent(capContentDir, fileEntry);
				populateWordsCount(fileEntry.getPath());
				if (words != null) {
					fileWriter.writeWordsSummary(wordsDir, words,
							fileEntry.getName());
				}
				if (uniqueWords != null) {
					fileWriter.writeWordsSummary(uniqueWordsDir,
							uniqueWords, fileEntry.getName());
				}
			}
		}

	}

	private void resetMap() {
		words.clear();
		uniqueWords.clear();
	}

	/**
	 * This method is used to Capitalize the content of files.
	 * 
	 * @param capContentDir
	 * @param fileEntry
	 * @throws IOException
	 */
	public void capitalizeContent(File targetDir, final File file)
			throws IOException {
		fileWriter.writeFiles(targetDir, file);
	}

	/**
	 * This method is used to get the words count.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public void populateWordsCount(String path) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new DataInputStream(new FileInputStream(path))));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ,.;:\"");
				while (st.hasMoreTokens()) {
					String word = st.nextToken();

					if (!words.isEmpty() && words.containsKey(word)) {
						words.put(word, words.get(word) + 1);
					} else {
						words.put(word, 1);
					}

				}

			}
			getUniqueWord(words);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	private void getUniqueWord(Map<String, Integer> words) {
		Iterator<Entry<String, Integer>> it = words.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> pairs = it.next();

			if (pairs.getValue() == 1) {
				uniqueWords.put(pairs.getKey(), pairs.getValue());
			}
		}
	}
}