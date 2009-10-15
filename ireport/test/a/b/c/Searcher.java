package a.b.c;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author ÀîÃ¯
 * @since 3.0
 * @version $Id: Searcher.java 2009 Oct 14, 2009 11:41:16 AM $
 */

// begin Searcher.java
public class Searcher {
	public static void main(String[] args) throws Exception {
		File indexDir = new File("F://index");
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
				+ (end - start) + " milliseconds) that matched query ¡®" + q
				+ "¡¯:");
		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			System.out.println(i+":"+doc.get("filename")+"-"+doc.get("allfilename"));
			
		}
	}
	
	public static List<String> searchToArray(File indexDir, String q) throws Exception {
		Directory fsDir = FSDirectory.getDirectory(indexDir, false);
		IndexSearcher is = new IndexSearcher(fsDir);
	
		Query query = null;
		if(q.indexOf("?")!=-1 || q.indexOf("*")!=-1){
			//Í¨Åä·û²éÑ¯
			query = new WildcardQuery(new Term("filename", q.toLowerCase()));	
		
		}else if(q.startsWith("#")){
			//ÏñËÆ²éÑ¯
			query = new FuzzyQuery(new Term("filename", q.toLowerCase().substring(1)));
		}else{
			//Âú×ãÇ°×º²éÑ¯
		    query = new PrefixQuery(new Term("filename", q.toLowerCase()));
		}
		//Query query = new FuzzyQuery(new Term("filename", q.toLowerCase()));
		//Query query = QueryParser.parse(q, "contents", new StandardAnalyzer());
		
		BooleanQuery.setMaxClauseCount(4000);
		
		long start = new Date().getTime();
		Hits hits = is.search(query);
		
		long end = new Date().getTime();
		System.err.println("Found " + hits.length() + " document(s) (in "
				+ (end - start) + " milliseconds) that matched query ¡®" + q
				+ "¡¯:");
		String[] model = null;
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < hits.length(); i++) {
			
			Document doc = hits.doc(i);
			if(doc.get("realfilename").indexOf("$")!=-1){
				continue;
			}
			list.add(doc.get("realfilename")+" - "+doc.get("allfilename"));
			//model[k]= doc.get("realfilename")+" - "+doc.get("allfilename");
			//k++;
			
		}
		//System.out.println();
		//System.out.println("I="+hits.length());
		//System.out.println("K="+k);
		return list;
	}
}

// end Searcher.java
