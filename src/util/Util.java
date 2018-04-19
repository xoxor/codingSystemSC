package util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Util {
	static CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder(); // or

	public static boolean isPureAscii(String v) {
		return asciiEncoder.canEncode(v);
	}

	public static LinkedList<Integer> parseChannelCoding(String codeField) {
		LinkedList<Integer> channels = new LinkedList<>();
		if (codeField.equals("")) {
			throw new RuntimeException("Bad channel coding syntax");
		}
		StringTokenizer st = new StringTokenizer(codeField);
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.matches("[rR]\\d+")) {
				s = s.replaceAll("[rR]", "");
				channels.add(Integer.parseInt(s));
				channels.add(1);
			} else if (s.matches("[hH]\\(\\d+,\\d+\\)")) {
				s = s.replaceAll("[hH]", "");
				s = s.replaceAll("[()]", "");
				StringTokenizer st1 = new StringTokenizer(s, " ,");
				channels.add(Integer.parseInt(st1.nextToken()));
				channels.add(Integer.parseInt(st1.nextToken()));
			} else {
				throw new RuntimeException("Bad channel coding syntax");
			}
		}
		return channels;
	}

	public static LinkedList<Integer>[] parseChannel(String s, LinkedList<String> series) {
		StringTokenizer st = new StringTokenizer(s, ";");
		int tok = st.countTokens();
		LinkedList<Integer>[] channels = new LinkedList[tok];
		for (int i = 0; i < channels.length; i++) {
			String t = st.nextToken();
			channels[i] = parseChannelCoding(t);
			series.add(t);
		}
		return channels;
	}

	public static double[] parseGE(String par) {
		double parErr[] = new double[4];
		StringTokenizer st = new StringTokenizer(par, " ,");
		if (st.countTokens() != 4) {
			throw new RuntimeException("at \""+par+"\" - insert 4 parameters");
		}
		parErr[0] = Double.parseDouble(st.nextToken());
		parErr[1] = Double.parseDouble(st.nextToken());
		parErr[2] = Double.parseDouble(st.nextToken());
		parErr[3] = Double.parseDouble(st.nextToken());
		return parErr;
	}

	public static double[] parseBin(String par) {
		double parErr[] = new double[1];
		parErr[0] = Double.parseDouble(par);
		return parErr;
	}

	public static double[][] parseError(String s, boolean bin) {
		StringTokenizer st = new StringTokenizer(s, ";");
		int tok = st.countTokens();
		if (tok < 1) {
			throw new RuntimeException("insert parameter");
		}
		double[][] par = bin ? new double[tok][1] : new double[tok][4];
		for (int i = 0; i < par.length; i++) {
			if (bin) {
				par[i] = parseBin(st.nextToken());
			} else {
				par[i] = parseGE(st.nextToken());
			}
		}
		return par;
	}

	public static String getExt(String fileName) {
		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		return extension;
	}

}
