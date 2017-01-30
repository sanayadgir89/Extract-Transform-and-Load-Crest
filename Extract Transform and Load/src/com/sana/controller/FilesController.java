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
public class FilesController {

	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main (String[] args) throws IOException{
        init();
        System.out.println(" DONE !!!!!!!!!!!!!");
    }


	/**
	 * This is initialization method.
	 * @throws IOException
	 */
	private static void init() throws IOException {
		FileService service = new FileServiceImpl();
		Properties prop = config(new Properties());
		File sourceDir = new File(prop.getProperty("sourceDir"));
		File capContentDir = new File(prop.getProperty("capContentDir"));
		File wordSumDir = new File(prop.getProperty("wordSumDir"));
		 	capContentDir.mkdirs();
        	 wordSumDir.mkdirs();
        	 service.listFiles(sourceDir , capContentDir , wordSumDir);
	}


	/**
	 * This method used to set config info.
	 * @param prop
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Properties config(Properties prop)
			throws FileNotFoundException, IOException {
		prop.setProperty("sourceDir", "input");
		prop.setProperty("capContentDir", "output/CapitalizeDirectory");
		prop.setProperty("wordSumDir", "output/WordSummaryDirectory");
		return prop;
	}
}