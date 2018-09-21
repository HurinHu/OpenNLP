import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;

public class NewsClassification {
	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream("model/news.bin");
			DoccatModel model = new DoccatModel(is);
			DocumentCategorizer doccat = new DocumentCategorizerME(model);
	        String[] docWords = readFile("data/news2.txt").replaceAll("[^A-Za-z]", " ").split(" ");
	        double[] aProbs = doccat.categorize(docWords);
	        System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
            for(int i=0;i<doccat.getNumberOfCategories();i++){
                System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
            }
            System.out.println("---------------------------------");
	        System.out.println(doccat.getBestCategory(aProbs)+" : is the predicted category for the given article.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static String readFile(String path) throws IOException{
		File file = new File(path);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
}
