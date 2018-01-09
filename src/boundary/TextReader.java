package boundary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TextReader {		
		
		public String[] textFromFile(String fileName) throws IOException {
			String[] outputArray;
		    
		    FileReader fr = new FileReader(fileName);
		    BufferedReader bf = new BufferedReader(fr);
		    FileReader fileA = new FileReader(fileName);
		    
		    outputArray = new String[countArray(fileA)];
		    
		    try {
		        int i=0;
		        
		        String line; 
		      
		        while((line = bf.readLine()) != null){
		        	outputArray[i] = line;
		            i++;
		        }
		        bf.close();

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
		 * Hjælpefunktion til main metoden.
		 * @param fileName
		 * @return
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


