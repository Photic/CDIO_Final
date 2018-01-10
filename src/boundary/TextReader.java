package boundary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TextReader {		

	
	/**
	 * This function collects text from a file, and returns it as an array, so it is usable in other places of the code
	 * @param fileName
	 * @return output Array
	 * @throws IOException
	 */
	
	public String[] textFromFile(String fileName) throws IOException {
		
		String[] outputArray;
		FileReader fr = new FileReader(fileName);										//Filereader fills a buffer with chars from a file
		BufferedReader bf = new BufferedReader(fr);										//BufferedReader reads from an inputstream
		
																						//return array is made with size of amount of chars in the inputstream from filereader
		FileReader fileA = new FileReader(fileName);
		outputArray = new String[countArray(fileA)];

		
		try {																			// try-catch to make sure that we know if the files cannot be read
			int i=0;
			String line; 

			while((line = bf.readLine()) != null){										//runs as long as the file does not end
				outputArray[i] = line;													//puts line of text in the return array
				i++;
			}
	
			bf.close();																	//closes bufferedreader 

		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputArray;
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


