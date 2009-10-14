package a.b.c;

import java.io.IOException;
import java.util.BitSet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.Filter;

public class ClassFileFilter extends Filter {

	@Override
	public BitSet bits(IndexReader reader) throws IOException {
		BitSet bits = new BitSet(reader.maxDoc());
		TermDocs doc = reader.termDocs(new Term("filename","R"));
		int count = doc.read(new int[1], new int[1]);
		System.out.println("------"+count);
		if(count!=0){
			bits.set(0);
		}
		return null;
	}

}
