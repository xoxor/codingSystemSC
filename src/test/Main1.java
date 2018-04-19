package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String fileName = null;
		int sourceCoding = 0;
		List<Integer> channels = new LinkedList<Integer>();
		double[] parErr = null;

		String s = null;
		int stato = 0;
		boolean fine = false;

		while (!fine) {
			try {
				switch (stato) {
				case 0:
					System.out.print("Inserisci tipo compressione (lzw o lzmw)>");
					s = sc.nextLine();
					if (s.equalsIgnoreCase("lzw")) {
						sourceCoding = 1;
						stato++;
					} else if (s.equalsIgnoreCase("lzmw")) {
						sourceCoding = 2;
						stato++;
					} else {
						System.out.println("Errore. Inserisci lzw o lzmw");
					}
					break;
				case 1:
					System.out.print("Inserisci prossima codifica canale (es r3 o h(7,3) oppure 0 per terminare)>");
					s = sc.nextLine();
					if (s.matches("[rR]\\d+")) {
						s = s.replaceAll("[rR]", "");
						System.out.println(s);
						channels.add(Integer.parseInt(s));
						channels.add(1);
					} else if (s.matches("[hH]\\(\\d+,\\d+\\)")) {
						s = s.replaceAll("[hH]", "");
						s = s.replaceAll("[()]", "");
						StringTokenizer st = new StringTokenizer(s, " ,");
						channels.add(Integer.parseInt(st.nextToken()));
						channels.add(Integer.parseInt(st.nextToken()));
					} else if (s.equals("0")) {
						stato++;
					} else {
						System.out.println("Errore");
					}
					break;
				case 2:
					System.out.print("Inserisci modello canale (b (Binary symmetric) o g (Gilbet-Elliot))>");
					s = sc.nextLine();
					if (s.equalsIgnoreCase("b")) {
						parErr = new double[1];
						System.out.print("Inserisci alpha>");
						parErr[0] = sc.nextDouble();
						stato++;
					} else if (s.equalsIgnoreCase("g")) {
						parErr = new double[4];
						System.out.print("Inserisci pgg>");
						parErr[0] = sc.nextDouble();
						System.out.print("Inserisci pbb>");
						parErr[1] = sc.nextDouble();
						System.out.print("Inserisci pg>");
						parErr[2] = sc.nextDouble();
						System.out.print("Inserisci pb>");
						parErr[3] = sc.nextDouble();
						stato++;
					} else {
						System.out.println("Errore");
					}
					break;
				case 3:
					System.out.print("Inserisci nome file>");
					sc = new Scanner(System.in);
					fileName = sc.nextLine();
					fine = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("Error ");
				System.out.println(e.getMessage());
				System.out.println("Riprova");
			}
		}

		int[] chann = new int[channels.size()];
		int i = 0;
		for (int p : channels) {
			chann[i++] = p;
		}
		Execution e = new Execution(fileName, sourceCoding, chann, parErr);
		e.execute();
	}

}
