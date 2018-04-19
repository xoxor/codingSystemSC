package test;

import java.util.*;

public class Main {
	/** convenient "-flag opt" combination */
	private class Option {
		String flag, opt;

		public Option(String flag, String opt) {
			this.flag = flag;
			this.opt = opt;
		}

		public String toString() {
			return flag + ", " + opt;
		}
	}

	public void mai(String[] args) {
		try {
			String s = "lzw";
			List<String> c = new ArrayList<String>();
			List<Double> m = new ArrayList<Double>();
			String fileName = null;

			char cur = ' ';

			for (int i = 0; i < args.length; i++) {
				if (i == args.length - 1) {
					fileName = args[i];
				} else {
					args[i] = args[i].replaceAll(" ", "");
				}
				switch (args[i].charAt(0)) {
				case '-':
					if (args[i].length() != 2) {
						throw new IllegalArgumentException("Not a valid argument: " + args[i]);
					} else {
						cur = args[i].charAt(1);
					}
					break;
				default:
					switch (cur) {
					case 's':
						s = args[i];
						break;
					case 'c':
						c.add(args[i]);
						break;
					case 'm':
						m.add(Double.parseDouble(args[i]));
						break;
					default:
						throw new IllegalArgumentException("Not a valid argument: " + args[i]);
					}
				}
			}

			int sourceCoding = s.equalsIgnoreCase("lzw") ? 1 : s.equalsIgnoreCase("lzmw") ? 2 : 0;
			int[] channels = new int[c.size() * 2];

			int i = 0;
			for (String r : c) {
				if (r.matches("[rR]\\d+")) {
					r = r.replaceAll("rR", "");
					channels[i++] = Integer.parseInt(r);
					channels[i++] = 1;
				} else if (r.matches("[hH](\\d+,\\d+)")) {
					r.replaceAll("hH()", "");
					StringTokenizer st = new StringTokenizer(r, " ,");
					channels[i++] = Integer.parseInt(st.nextToken());
					channels[i++] = Integer.parseInt(st.nextToken());
				} else {
					throw new IllegalArgumentException("Not a valid argument: " + r);
				}
			}

			double[] parErr = new double[m.size()];
			i = 0;
			for (double d : m) {
				parErr[i] = d;
			}
			Execution e = new Execution(fileName, sourceCoding, channels, parErr);
			e.execute();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("Riprova. Sintassi");
		}

	}

	public static void main(String[] args) {
		Main r = new Main();
		r.mai(new String[] { "-c" });
	}
}