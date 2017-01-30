package com.sana.service.impl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sana.service.FileService;
/**
 *
 * @author Sana Yadgir
 */
public class FileServiceImpl  implements FileService{


	/**
     * List all the files under a directory
     * @param wordSumDir 
     * @param capContentDir 
     * @param directoryName to be listed
     * @throws IOException 
     */
    public void listFiles(final File sourceDir, File capContentDir, File wordSumDir) throws IOException {
    	
    		System.out.println( " Directory Files" );
    			for (final File fileEntry : sourceDir.listFiles()) {
    				System.out.println("FILE NAME  :"   + fileEntry);
    	                capitalizeContent(capContentDir, fileEntry);
    	                 this.writeSummary(wordSumDir , getWordsAndCount(fileEntry.getPath()) , fileEntry.getName()) ; 
    	    }
    	
    	}

    /**
     * This method is used to Capitalize the content of files.
     * @param capContentDir
     * @param fileEntry
     * @throws IOException
     */
    public void capitalizeContent(File capContentDir, final File fileEntry) throws IOException
	{
		File outputFile = new File(capContentDir , fileEntry.getName());
		BufferedReader in  = null;
		PrintWriter out = null;
			if (!outputFile.exists()) {
					outputFile.createNewFile();
			}
			 in = new BufferedReader(new FileReader(fileEntry));
			 out = (new PrintWriter(new FileWriter(outputFile)));
			int ch;
			while ((ch = in.read()) != -1)
			{
			        ch=Character.toUpperCase(ch);
			        out.write(ch);
			}
			
			in.close();
			out.close();
	}

  
	/**
	 * This method is used to write the word count summary details
	 * @param wordSumDir
	 * @param wordsAndCount
	 * @param fileName
	 * @throws IOException
	 */
    public void writeSummary(File wordSumDir, Map<String, Integer> wordsAndCount, String fileName) throws IOException  {
    	Map<String, Integer> uniquewords = new HashMap<String, Integer>();
    	 File outputFile = new File(wordSumDir , fileName);
         if (!outputFile.exists()) {
         	outputFile.createNewFile();
         }
         BufferedWriter  out = new BufferedWriter(new FileWriter(outputFile.getPath()));
 	    Iterator<Entry<String, Integer>> it = wordsAndCount.entrySet().iterator();
 	   out.write("All words Summary----------------------------------------------- START\n\n");
 	   out.write("WORD												COUNT\n\n");
 	   out.write("------------------------------------------------------------------\n");
 	    while (it.hasNext()) {
 	        Entry<String, Integer> pairs = it.next();
 	      
 	        out.write(pairs.getKey() + "												" + pairs.getValue() + "\n");
 	        if(pairs.getValue() == 1 ){
 	        	uniquewords.put(pairs.getKey(), pairs.getValue());
 	        }
 	        
 	    }
 	   out.write("\nAll words Summary----------------------------------------------- END\n\n\n\n");
 	  writeUniqueWords(uniquewords, out);
 	    out.close();
		
	}

    /**
     * This method is used to write unique words
     * @param uniquewords
     * @param out
     * @throws IOException
     */
    public void writeUniqueWords(Map<String, Integer> uniquewords,
			BufferedWriter out) throws IOException {
		out.write("Unique words Summary----------------------------------------------- START\n\n");
		out.write("UNIQUE WORDS						\n\n");
		out.write("------------------------------------------------------------------\n");
		  Iterator<Entry<String, Integer>> wordIt = uniquewords.entrySet().iterator();
		    while (wordIt.hasNext()) {
		        Entry<String, Integer> pairs = wordIt.next();
		        out.write(pairs.getKey() + "\n");
		    }
		  out.write("\nUnique words Summary ----------------------------------------------- END");
	}


	/**
	 * This method is used to get the words count.
	 * @param path
	 * @return
	 * @throws IOException
	 */
    public Map<String, Integer>  getWordsAndCount(String path ) throws IOException {
    	Map<String, Integer> wordsAndCount = new HashMap<String, Integer>();
    	FileInputStream  fis = new FileInputStream(path);
    	DataInputStream  dis = new DataInputStream(fis);
    	BufferedReader  br = new BufferedReader(new InputStreamReader(dis));
           String line = null;
           try{
           while((line = br.readLine()) != null){
               StringTokenizer st = new StringTokenizer(line, " ,.;:\"");
               while(st.hasMoreTokens()){
                   String word = st.nextToken().toLowerCase();
                   if (wordsAndCount.containsKey(word)){ //If word is in our map already, increase count
                	   wordsAndCount.put(word, wordsAndCount.get(word)+1);
                   }else{  //If not in our map, add it and set count to 1
                	   wordsAndCount.put(word, 1);
                   }
               }
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
	      finally{
	           try{if(br != null) br.close();}catch(Exception ex){}
	       }
         
		return wordsAndCount;
	
	}
}