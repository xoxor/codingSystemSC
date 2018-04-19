package sourcecoding;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import test.Statistics;
import util.BitStream;
import util.Util;

public class Lzw extends SourceCoding {

	public Lzw(Statistics stats) {
		super(stats);
	}

	public BitStream compress(String in, boolean file) {
		initInput(in, file);
		// init dictionary
		// symbol --> code
		LinkedHashMap<String, Integer> dict = new LinkedHashMap<String, Integer>();
		initDictCod(dict);

		output = new BitStream(codelen);
		String w = "";
		char[] chars = input.toCharArray();
		for (char k : chars) {
			String wc = w + k;
			if (dict.containsKey(wc))
				w = wc;
			else {
				output.append(dict.get(w));
				dict.put(wc, dict.keySet().size());
				w = "" + k;
				if (dict.keySet().size() == this.dictMax) {
					initDictCod(dict);
				}
			}
		}
		// Output the code for w.
		if (!w.equals("")) {
			output.append(dict.get(w));
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
		initDictDec(dict);

		String w = "" + dict.get((compressed.remove(0)));
		StringBuilder decompressed = new StringBuilder();
		decompressed.append(w);
		for (int k : compressed) {
			String entry;
			if (dict.containsKey(k))
				entry = dict.get(k);
			else if (k == dict.keySet().size())
				entry = w + w.charAt(0);
			else
				throw new IllegalArgumentException("Bad compressed k: " + k);
			decompressed.append(entry);
			dict.put(dict.keySet().size(), w + entry.charAt(0));
			if (dict.keySet().size() == this.dictMax) {
				initDictDec(dict);
			}
			w = entry;
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
