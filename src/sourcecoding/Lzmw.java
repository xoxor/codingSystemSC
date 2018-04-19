package sourcecoding;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import test.Statistics;
import util.BitStream;
import util.Trie;
import util.Util;

public class Lzmw extends SourceCoding {

	public Lzmw(Statistics stats) {
		super(stats);
	}

	public void initTrie(Trie tr) {
		tr.clear();
		for (int i = 0; i < 256; i++) {
			tr.insert("" + (char) i);
		}
	}

	public BitStream compress(String in, boolean file) {
		initInput(in, file);
		// init dictionary;
		// symbol --> code
		Trie tr = new Trie();
		LinkedHashMap<String, Integer> dict = new LinkedHashMap<String, Integer>();
		initDictCod(dict);
		initTrie(tr);

		output = new BitStream(codelen);
		String ss = "";
		String s = "";
		StringBuilder inp = new StringBuilder(input);
		int i = 0;
		int k;
		while (i < input.length()) {
			s = tr.getMatchingPrefix(inp.delete(0, s.length()));
			k = dict.get(s);
			output.append(k);
			i += s.length();
			if (!dict.containsKey(ss + s)) {
				dict.put(ss + s, dict.keySet().size());
				tr.insert(ss + s);
				if (dict.keySet().size() == this.dictMax) {
					initDictCod(dict);
					initTrie(tr);
				}
			}
			ss = s;
		}
		
		String name = "";
		String ext = "txt";
		if (fileName != null) {
			name = fileName.substring(0, fileName.lastIndexOf('.'));
		}
		writeByte(output.word.toByteArray(), name + "_compressed."+ext);
		
		// printDic(dict);
		stats.setCompressionStats(this.input, this.s, output, this.codelen);
		return output;
	}

	public String decompress(BitStream compr) {
		ArrayList<Integer> compressed = new ArrayList<Integer>();
		for (int i = 0; i < compr.size(); i++) {
			compressed.add(compr.getInt(i));
		}

		// init dictionary
		// CODE-->SYMBOL (al contrario rispetto a compress)
		LinkedHashMap<Integer, String> dict = new LinkedHashMap<Integer, String>();
		LinkedHashMap<String, Integer> dict2 = new LinkedHashMap<String, Integer>();
		initDictCod(dict2);
		initDictDec(dict);

		int nextIndex = dict.keySet().size();
		String s = dict.get((compressed.remove(0)));
		String ss = "" + s;
		StringBuilder decompressed = new StringBuilder();
		decompressed.append(s);
		for (int k : compressed) {
			if (dict.containsKey(k)) {
				s = dict.get(k);
			} else if (k == dict.keySet().size()) {
				s = s + s.charAt(0);
			} else
				throw new IllegalArgumentException("Bad compressed k: " + k);

			decompressed.append(s);
			if (!dict2.containsKey(ss + s)) {
				dict.put(nextIndex, ss + s);
				dict2.put(ss + s, nextIndex);
				nextIndex++;
			}
			if (dict.keySet().size() == this.dictMax) {
				initDictDec(dict);
				initDictCod(dict2);
				nextIndex = dict.size();
			}
			ss = s;

		}
		String name = "";
		String ext = "txt";
		if (fileName != null) {
			name = fileName.substring(0, fileName.lastIndexOf('.'));
			ext = Util.getExt(fileName);
		}
		write(decompressed.toString(), name + "_decompressed."+ext);
		return decompressed.toString();
	}

}
