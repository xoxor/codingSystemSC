package test;

import sourcecoding.*;
import util.BitStream;

public class TestCompression {
	public static void main(String[] args) {

		Statistics stats = new Statistics();

		String input = "text1.txt";

		SourceCoding l = new Lzw(stats);

		BitStream compressed = l.compress(input, true);
		System.out.println(compressed);

		String dec = l.decompress(compressed);
		System.out.println(dec);

		stats.printStat();

		/*
		 * input = "TOBEORNOTTOBEORTOBEORNOT"; compressed = l.compress(input, false);
		 * System.out.println(compressed); dec = l.decompress(compressed);
		 * System.out.println(dec); l.printStat();
		 */

	}
}
