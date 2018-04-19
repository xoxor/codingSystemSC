package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Test {
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		pw = new PrintWriter(new FileWriter("stat.txt"));
		// generate();
		//testCompression();
		test5();
		pw.close();
	}

	public static void testCompression() {
		int compressione = 2;
		double[] parErr = { 0.0 };
		int[] channels = { 3, 1 };
		String[] fileName = { "romeojuliet.txt", "othello.txt", "dracula.txt",
				"andersenfairytales.txt",  "keats.txt",
				"pirandsei.txt", "marinetti.txt", "avilanza.txt", "divinacom.txt",
				"pinocchio.txt",
				"random.txt", "random-text.txt", "random-text-280k.txt",
				"cesare.txt",
				"teoinfo.tex",
				"Formatter.java", "Arrays.java"};

		Execution e = null;
		for (int i = 0; i < fileName.length; i++) {
			e = new Execution(fileName[i], compressione, channels, parErr);
			e.execute();
			// sym iniz, bit per sym, dim init sym finali, codelength, fattore
			// compressione
			String s = fileName[i] + "\t" + e.stats.inputsymbols + "\t" + e.stats.inputbitsymbols + "\t"
					+ e.stats.inputSize / 8 + "\t" + e.stats.outputsymbols + "\t" + e.stats.outputbitsymbols + "\t"
					+ e.stats.outputSize / 8 + "\t" + e.stats.compressionratio + "\t" + +e.stats.time;
//			String s = "\\hline "+ fileName[i] + " & $" + e.stats.inputsymbols + "$ & $" + e.stats.inputbitsymbols + "$ & $"
//					+ e.stats.inputSize / 8 + "$ & $" + e.stats.outputsymbols + "$ & $" + e.stats.outputbitsymbols + "$ & $"
//					+ e.stats.outputSize / 8 + "$ & $" + String.format("%.6f\n", e.stats.compressionratio) + "$ & $" + +e.stats.time+"$ \\\\";
			System.out.println(s);
			pw.println(s);
		}
	}

	// input, compressione ed errore fissi
	// valutazione concatenazione di piu canali di codifica
	public static void test2() {
		int compressione = 1;
		double[] parErr = { 0.1 };
		int[] channels = { 7, 4 };
		String fileName = "romeojuliet.txt";
		Execution e = null;
		for (int i = 3; i <= 35; i += 2) {
			// channels[0] = i;
			int sum1 = 0;
			double sum2 = 0;
			int n = 5;
			for (int j = 0; j < n; j++) {
				e = new Execution(fileName, compressione, channels, parErr);
				e.execute();
				sum1 += e.stats.codingErrors;
				sum2 += e.stats.time;
			}
			String s = "R" + channels[0] + "\t" + sum1 / n + "\t" + sum2 / n;
			System.out.println(s);
			pw.println(s);
		}
	}

	public static void test3() {
		test3(new int[] { 3, 1, 3, 1 });
		test3(new int[] { 3, 1, 5, 1 });
		test3(new int[] { 5, 1, 3, 1 });
		test3(new int[] { 3, 1, 7, 1 });
		test3(new int[] { 7, 1, 3, 1 });
		test3(new int[] { 5, 1, 7, 1 });
		test3(new int[] { 7, 1, 5, 1 });
	}

	// input, compressione ed errore fissi
	// valutazione concatenazione di piu canali di codifica
	public static void test3(int channels[]) {
		int compressione = 1;
		double[] parErr = { 0.6 };
		String fileName = "romeojuliet.txt";
		Execution e = null;
		int sum1 = 0;
		double sum2 = 0;
		int n = 5;
		for (int j = 0; j < n; j++) {
			e = new Execution(fileName, compressione, channels, parErr);
			e.execute();
			sum1 += e.stats.codingErrors;
			sum2 += e.stats.time;
		}
		String s = "R" + channels[0] + "-" + channels[2] + "\t" + sum1 / n + "\t" + sum2 / n;
		System.out.println(s);
		pw.println(s);
	}

	public static void test4() {
		int compressione = 1;
		double[] parErr = { 0 };
		int[] channels = { 3, 1 };
		String fileName = "ipromessisposi.txt";
		Execution e = null;
		e = new Execution(fileName, compressione, channels, parErr);
		e.execute();
		System.out.println(e.stats.codingErrors);
		System.out.println(e.stats.time);
	}

	// simmetrio binario, varia alpha, 7,4; canale GE al variare dei parametri
	// pg e pb
	public static void test5() {
		double[] parErr = { 0.995, 0.96, 0.0001, 0.1 };
		String fileName = "romeojuliet.txt";
		Execution e1 = null;
		Execution e2 = null;
		for (int i = 0; i < 10; i++) {
			//parErr[1] *= 0.5;
			int media1 = 0;
			int media2 = 0;
			int n = 3;
			for (int j = 0; j < n; j++) {
				e1 = new Execution(fileName, 1, new int[] { 7, 4 }, parErr);
				e1.execute();
				e2 = new Execution(fileName, 1, new int[] { 3, 1 }, parErr);
				e2.execute();
				media1 += e1.stats.codingErrors;
				media2 += e2.stats.codingErrors;
			}
			media1 /= n;
			media2 /= n;
			String s = parErr[1] + "\t" + media1 + "\t" + media2 + "\t" + media1 / (double) e1.stats.outputSize + "\t"
					+ media2 / (double) e2.stats.outputSize;
			System.out.println(s);
			pw.println(s);
			parErr[1] -= 0.1; //piu giusto qua
		}
	}

	public static void generate() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("random.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random r = new Random();
		int Low = 32;
		int High = 127;
		for (int i = 0; i < 160000; i++) {
			char a = (char) (r.nextInt(High - Low) + Low);
			// System.out.println(a);
			pw.write(a);
		}
		pw.close();
	}

}
