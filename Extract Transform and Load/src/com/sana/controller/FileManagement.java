package com.sana.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.sana.service.FileService;
import com.sana.service.impl.FileServiceImpl;

/**
 *
 * @author Sana Yadgir
 */
public class FileManagement {

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("START !!!!!!!!!!!");
		init();
		System.out
				.println("DONE Please find result in the output folder !!!!!!!!!!!");
	}

	/**
	 * This is initialization method.
	 * 
	 * @throws IOException
	 */
	private static void init() throws IOException {
		FileService service = new FileServiceImpl();
		Properties prop = config(new Properties());
		File sourceDir = new File(prop.getProperty("sourceDir"));
		File capContentDir = new File(prop.getProperty("capContentDir"));
		File wordsDir = new File(prop.getProperty("wordsDir"));
		File uniqueWordsDir = new File(prop.getProperty("uniqueWordsDir"));
		createTargetDirs(capContentDir, wordsDir , uniqueWordsDir);
		service.listFiles(sourceDir, capContentDir, wordsDir , uniqueWordsDir);
	}

	/**
	 * This method is used to create the Target Directories.
	 * 
	 * @param capContentDir
	 * @param wordSumDir
	 */
	private static void createTargetDirs(File capContentDir, File wordsDir , File uniqueWordsDir) {
		capContentDir.mkdirs();
		wordsDir.mkdirs();
		uniqueWordsDir.mkdir();
	}

	/**
	 * This method used to set config info.
	 * 
	 * @param prop
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Properties config(Properties prop)
			throws FileNotFoundException, IOException {
		prop.setProperty("sourceDir", "input");
		prop.setProperty("capContentDir", "output/CapitalizeDirectory");
		prop.setProperty("wordsDir", "output/WordsDirectory");
		prop.setProperty("uniqueWordsDir", "output/UniqueWordsDirectory");
		return prop;
	}

}