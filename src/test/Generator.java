package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;


public class Generator {

	
	public static void main (String [] args) {
		String in="common_ita.txt";
		String out="random_text.txt";
		LinkedList<String> ls= read(in);
		write(ls, out);
	}
	
	
	public static LinkedList<String> read(String in) {	
		LinkedList<String> ls= new LinkedList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(in))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       ls.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	
	public static void write(LinkedList<String> ls, String filename) {
		int n= ls.size();
		Random random = new Random();
		try {
			FileOutputStream fos = new FileOutputStream(new File(filename));
			for (int i=0; i<18300; i++) {
				int r = random.nextInt(n);
				fos.write(ls.get(r).getBytes());
				fos.write(32);
			}
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
