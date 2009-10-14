package a.b.c;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: Searcher.java 2009 Oct 14, 2009 11:41:16 AM $
 */

// begin Searcher.java
public class Searcher {
	public static void main(String[] args) throws Exception {
		File indexDir = new File("G://index");
		String q = "RTXSvrApi.class";
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir
					+ " does not exist or is not a directory.");
		}
		search(indexDir, q);
	}

	public static void search(File indexDir, String q) throws Exception {
		Directory fsDir = FSDirectory.getDirectory(indexDir, false);
		IndexSearcher is = new IndexSearcher(fsDir);
		
		Query query = new WildcardQuery(new Term("filename", "*R*"));
		
		//Query query = QueryParser.parse(q, "contents", new StandardAnalyzer());


		long start = new Date().getTime();
		Hits hits = is.search(query,new Filter(){

			@Override
			public BitSet bits(IndexReader arg0) throws IOException {
				return null;
			}
			
		});
		
		long end = new Date().getTime();
		System.err.println("Found " + hits.length() + " document(s) (in "
				+ (end - start) + " milliseconds) that matched query °Æ" + q
				+ "°Ø:");
		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			System.out.println(i+":"+doc.get("filename")+"-"+doc.get("allfilename"));
			
		}
	}
	
	public static String[] searchToArray(File indexDir, String q) throws Exception {
		Directory fsDir = FSDirectory.getDirectory(indexDir, false);
		IndexSearcher is = new IndexSearcher(fsDir);
		q = "*"+q+"*";
		Query query = new WildcardQuery(new Term("filename", q));
		
		//Query query = QueryParser.parse(q, "contents", new StandardAnalyzer());


		long start = new Date().getTime();
		Hits hits = is.search(query,new Filter(){

			@Override
			public BitSet bits(IndexReader arg0) throws IOException {
				return null;
			}
			
		});
		
		long end = new Date().getTime();
		System.err.println("Found " + hits.length() + " document(s) (in "
				+ (end - start) + " milliseconds) that matched query °Æ" + q
				+ "°Ø:");
		String[] model = null;
		if(hits.length()>0){
			model = new String[hits.length()];
		}
		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			model[i]= doc.get("filename")+"-"+doc.get("allfilename");
			
		}
		return model;
	}
}

// end Searcher.java
