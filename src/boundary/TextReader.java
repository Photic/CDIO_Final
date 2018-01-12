package boundary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

}


