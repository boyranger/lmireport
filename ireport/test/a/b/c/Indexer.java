package a.b.c;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

//import org.jgroups.tests.perf.Data;

/**
 * @author ¿Ó√Ø
 * @since 3.0
 * @version $Id: Indexer.java 2009 Oct 14, 2009 11:23:19 AM $
 */

// begin Indexer.java
public class Indexer {
	public static void main(String[] args) throws Exception {
		
		File indexDir = new File("G:/index");
		File dataDir = new File("D:\\workspace-bak\\app\\creatorepp\\WEB-INF\\classes");
		long start = new Date().getTime();
		int numIndexed = index(indexDir, dataDir);
		long end = new Date().getTime();
		System.out.println("Indexing" + numIndexed + " files took"
				+ (end - start) + " milliseconds");
	}

	public static int index(File indexDir, File dataDir) throws IOException {
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(dataDir
					+ " does not exist or is not a directory");
		}
		IndexWriter writer = new IndexWriter(indexDir, new StandardAnalyzer(),
				true);
		writer.setUseCompoundFile(false);
		indexDirectory(writer, dataDir);
		int numIndexed = writer.docCount();
		writer.optimize();
		writer.close();
		return numIndexed;
	}

	private static void indexDirectory(IndexWriter writer, File dir)
			throws IOException {
		
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				
				indexDirectory(writer, f);
			} else if (f.getName().endsWith(".class")) {
				indexFile(writer, f);
			}
		}
	}

	private static void indexFile(IndexWriter writer, File f)
			throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}
		String filepath = f.getCanonicalPath();
		String filename = getClassFullName(filepath);
		System.out.println("Indexing["+filename+"]" + filepath);
		Document doc = new Document();
		//doc.add(Field.Text("contents", f.getName()));
		doc.add(Field.Keyword("allfilename", filename));
		doc.add(Field.Keyword("filename", f.getName().substring(0, f.getName().length()-6)));
		writer.addDocument(doc);
	}
	
	private static String getClassFullName(String path){
		String classPath = path.split("classes")[1];
		//System.out.println(classPath.length());
		classPath = classPath.substring(1, classPath.length()-6);
		
		classPath = classPath.replaceAll("\\\\", ".");
		return classPath;
	}
}

// end Indexer.java
