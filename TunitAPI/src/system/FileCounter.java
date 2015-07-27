package system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileCounter {
	
	private String fileName = "FileCounter.initial";
	
	public int getCount() {
	    int count = 0;
	    
	    // Load the file with the counter
	    FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    PrintWriter writer = null;
	    
	    try {
	    	  File file = new File(fileName);
		      if (!file.exists()) {
		    	  	file.createNewFile();
			        writer = new PrintWriter(new FileWriter(file));
			        writer.println(0);
		      }
		      if (writer != null) {
		    	  writer.close();
		      }
		      fileReader = new FileReader(file);
		      bufferedReader = new BufferedReader(fileReader);
		      String initial = bufferedReader.readLine();
		      count = Integer.parseInt(initial);
	    } catch (Exception ex) {
		      if (writer != null) {
		    	  writer.close();
		      }
	    }
	    if (bufferedReader != null) {
		      try {
		    	  bufferedReader.close();
		      } catch (IOException e) {
		    	  e.printStackTrace();
		      }
	    }
	    return count;
	  }

	  public void save(int count) throws Exception {
		    FileWriter fileWriter = null;
		    PrintWriter printWriter = null;
		    fileWriter = new FileWriter(fileName);
		    printWriter = new PrintWriter(fileWriter);
		    printWriter.println(count);
		    
		    // make sure to close the file
		    if (printWriter != null) {
		    		printWriter.close();
		    }
	  }
}
