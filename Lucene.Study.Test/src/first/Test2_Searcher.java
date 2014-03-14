package first;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Test2_Searcher {
	
	public static void main(String[] args){
		try {
			Search("��");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Search(String querystring) throws IOException, ParseException{
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("d:\\lucene\\index")));
		IndexSearcher search = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		
		QueryParser parser = new QueryParser(Version.LUCENE_47,"content",analyzer);
		
		Query query = parser.parse(querystring);
		
		TopDocs results = search.search(query, 5);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits);
		for(ScoreDoc hit : hits){
			Document doc = search.doc(hit.doc);
			System.out.println("======title:" + doc.get("title"));
			System.out.println("======content:" + doc.get("content"));
		}
		
	}

}
