package boundary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TextReader {		
	
	
	public String[] textFromFile(InputStream in) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		StringBuilder out = new StringBuilder();
		String line;
		
		while ((line = reader.readLine()) != null) 
            out.append(line);
        
		
		String output = out.toString();
		
		
		reader.close();
		return output.split("_");
	}

	/**
	 * Prints the array.
	 * @param a
	 */
	 public void printArray(String[] a) {
		for (int i = 0; i<a.length; i++) {
			System.out.println(a[i]);
		}
	 }


	 /**
	  * This method calculates how many lines of text that the textreader will load, to make sure that the array that it returns is the propper size, 
	  * and doesnt have spaces with null
	  * @param fileName
	  * @return int arrayCount
	  * @throws IOException
	  */
	 private int countArray(Reader fileName) throws IOException{

		 BufferedReader tempFile = new BufferedReader(fileName);
		 int arrayCount = 0;
		 String line = tempFile.readLine();
		 
		 while(line!=null){
			 line = tempFile.readLine();
			 arrayCount++;
		 }

		 return arrayCount; 
	 }




}


