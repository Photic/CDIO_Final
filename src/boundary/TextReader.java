package boundary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TextReader {		

	
	/**
	 * Funktionen henter teksen fra en fil, og returnere det som et array, så det kan bruges andre steder i koden
	 * 
	 * @param fileName
	 * @return output Array
	 * @throws IOException
	 */
	
	public String[] textFromFile(String fileName) throws IOException {
		String[] outputArray;

		//Filereader fylder sin buffer op med karaktere fra en given fil
		FileReader fr = new FileReader(fileName);
		
		//BufferedReader indlæser fra en inputstream, som i det her tilfælde er en FileReader
		BufferedReader bf = new BufferedReader(fr);
		
		
		//return array instantieres med størelsen af hvor mange linjer tekst der er at hente ind fra filen
		FileReader fileA = new FileReader(fileName);
		outputArray = new String[countArray(fileA)];

		//Try Catch bruges til enten at udføre funktionen eller give en besked uden at breake programmet
		try {
			int i=0;
			String line; 

			while((line = bf.readLine()) != null){
				outputArray[i] = line;
				i++;
			}
			//Her lukkes BufferedReader manuelt, så javas trashcollecter ikke behøver gøre det 
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
	  * Hjælpefunktion til main metoden. Metoden er til for at man ikke skal lave et array af en fast størrelse. 
	  * Lige meget hvor stor en textfil man indlæser vil ovenstående metode returnere et array uden null på nogle af pladserne 
	  * 
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


