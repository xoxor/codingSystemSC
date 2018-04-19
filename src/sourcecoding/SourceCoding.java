package sourcecoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import test.Statistics;
import util.BitStream;
import util.Util;

public abstract class SourceCoding {

	// bit per symbols (dell'alfabeto iniziale)
	int s;

	// bit per entries of the dictionary (codifica dell'alfabeto esteso).
	int codelen;
	int dictMax;

	String fileName;
	String input;

	// output: lista di indici del dizionario
	BitStream output;

	Statistics stats;

	public static boolean isPureAscii(String v) {
		CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
		return asciiEncoder.canEncode(v);
	}

	public SourceCoding(Statistics stats) {
		// ASCII 8-bit
		this.s = 8;
		// dizionario con al massimo 2^codelen simboli. 
		this.codelen = 16;
		this.stats = stats;
		this.dictMax = (int) Math.pow(2, this.codelen);
	}

	public void initInput(String in, boolean file) {
		if (file) {
			try {
				fileName = in;
				input = new String(Files.readAllBytes(Paths.get(in)));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			input = in;
		}
		if (input.equals("")) {
			throw new RuntimeException("Empty text file");
		}
		if (!Util.isPureAscii(input)) {
			throw new RuntimeException("Not ASCII text file");
		}
	}

	public void initDictCod(LinkedHashMap<String, Integer> dict) {
		// init ASCII-8
		dict.clear();
		for (int i = 0; i < 256; i++) {
			dict.put("" + (char) i, i);
		}
	}

	public void initDictDec(LinkedHashMap<Integer, String> dict) {
		// init ASCII-8
		dict.clear();
		for (int i = 0; i < 256; i++) {
			dict.put(i, "" + (char) i);
		}
	}

	public abstract BitStream compress(String input, boolean file);

	public abstract String decompress(BitStream compressed);

	public void write(String s, String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(filename));
			for (char a : s.toCharArray()) {
				fos.write(a);
			}
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeByte(byte[] s, String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(filename));
			fos.write(s);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printDic(LinkedHashMap<String, Integer> dict) {
		System.out.println("Dict size: " + dict.keySet().size());
		System.out.println("index\tstring\tcode");
		for (String key : dict.keySet()) {
			System.out.println(dict.get(key) + "\t" + key + "\t" + dict.get(key));
		}
	}
}
