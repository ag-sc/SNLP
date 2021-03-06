import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException{
			
		TigerCorpusReader reader = new TigerCorpusReader();
		
		reader.read("/Users/cimiano/data/tigercorpus2/corpus/tiger_release_dec05.xml");
		
		BufferedWriter output = new BufferedWriter(new FileWriter("/Users/cimiano/data/tigercorpus2/corpus/tiger_release_dec05.xml.csv"));
        
		reader.toFile(output);
        
        output.close();
       
		TaggerEvaluator evaluator = new TaggerEvaluator();
			
	    List<TaggedSentence> train = reader.getSentences(0, 50000);
	
	     
	    POS_Tagger tagger = new HMM_Tagger();
	    
	   
	    tagger.train(train);
	       
	    List<TaggedSentence> test = reader.getSentences(50001, reader.size());
	
	    evaluator.evaluate(tagger, test);
	    
	    System.out.print(evaluator.getStatistics());
	    
	    
	}

}
