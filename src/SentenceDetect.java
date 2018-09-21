import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetect {

	public static void main(String[] args) {
		try {
			new SentenceDetect().sentenceDetect();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void sentenceDetect() throws InvalidFormatException, IOException {
        String paragraph = "Apache openNLP supports the most common NLP tasks, such as tokenization, sentence segmentation, part-of-speech tagging, named entity extraction, chunking, parsing, and coreference resolution. These tasks are usually required to build more advanced text processing services. OpenNLP also includes maximum entropy and perceptron based machine learning.";

        InputStream is = new FileInputStream("definedmodel/en-sent.bin");
        SentenceModel model = new SentenceModel(is);

        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(paragraph);

        for(int i=0;i<sentences.length;i++){
            System.out.println(sentences[i]);
        }
        is.close();
    }
}
