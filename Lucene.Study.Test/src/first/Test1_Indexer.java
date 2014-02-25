package first;

import java.io.File;  
import java.io.IOException; 
import org.apache.lucene.analysis.Analyzer;  
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer; 
import org.apache.lucene.document.Document; 
import org.apache.lucene.document.Field; 
import org.apache.lucene.index.IndexWriter; 
import org.apache.lucene.index.IndexWriterConfig; 
import org.apache.lucene.store.Directory; 
import org.apache.lucene.store.FSDirectory; 
import org.apache.lucene.util.Version;

public class Test1_Indexer {

	public static void Indexer() throws IOException{
		File indexDir = new File("d:\\lucene\\index");
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46,true);
		IndexWriterConfig indexWriterconfig = new IndexWriterConfig(
				Version.LUCENE_46,analyzer);
		Directory directory = FSDirectory.open(indexDir);
		if(IndexWriter.isLocked(directory)){
			IndexWriter.unlock(directory);
		}
		IndexWriter writer = new IndexWriter(directory,indexWriterconfig);
		writer.deleteAll();
		
		Document doc = new Document();
		doc.add(new Field("content", "中华人民共和国",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc);
		Document doc1 = new Document();
		doc1.add(new Field("content", "美利坚合众国",Field.Store.YES,Field.Index.ANALYZED));
		writer.addDocument(doc1);
		
		int numIndexed = writer.numDocs();
		System.out.println(numIndexed);
		
		writer.close();
		
	}
	
	public static void main(String[] args){
		try {
			Indexer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
