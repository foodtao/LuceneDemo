package first;

import java.io.File;  
import java.io.IOException; 

import org.apache.lucene.analysis.Analyzer;  
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer; 
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document; 
import org.apache.lucene.document.Field; 
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter; 
import org.apache.lucene.index.IndexWriterConfig; 
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory; 
import org.apache.lucene.store.FSDirectory; 
import org.apache.lucene.util.Version;

public class Test1_Indexer {

	public static void Indexer() throws IOException{
		
		Directory dir = FSDirectory.open(new File("d:\\lucene\\index"));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter writer = new IndexWriter(dir,iwc);
		
		Document doc = new Document();
		Field field1 = new StringField("titile", "国家1" ,Field.Store.YES);
		doc.add(field1);
		Field field2 = new StringField("content", "中华人民共和国", Field.Store.YES);
		doc.add(field2);
		writer.addDocument(doc);
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
