package channelcoding;

import java.util.BitSet;

import test.Statistics;
import util.BitMat;
import util.BitStream;

public class HammingCode extends ChannelCoding {

	BitMat G;
	BitMat H;
	BitMat R;

	public HammingCode(int n, int k, Statistics s) {
		super(n, k, s);
		int q = n - k;
		if (n - k < 3) {
			throw new RuntimeException("n - k < 3");
		}

		if (n != (Math.pow(2, q) - 1)) {
			throw new RuntimeException("n != 2^q - 1");
		}

		// costruisce H
		this.H = new BitMat(q, n);
		for (int i = 0; i < n; i++) {
			int d = 0;
			int value = i + 1;
			while (value != 0) {
				if (value % 2 != 0) {
					H.set(d, i);
				}
				d++;
				value = value >>> 1;
			}
		}

		// costruisce G e R
		this.G = new BitMat(k, n);
		this.R = new BitMat(k, n);
		int[] p = new int[(int) Math.ceil(Math.log(n) / Math.log(2))];
		int[] np = new int[n - p.length];
		for (int i = 1, j = 0, r = 0; i <= n; i++) {
			if ((i & (i - 1)) == 0) {
				p[j++] = i - 1;
			} else {
				np[r++] = i - 1;
			}
		}

		int t = 0;
		for (int i : np) {
			G.set(t, i);
			R.set(t, i);
			t++;
		}

		for (int i = 0; i < p.length; i++) {
			t = 0;
			for (int j : np) {
				G.set(t++, p[i], H.get(i, j));
			}
		}
		H = H.transpose();
		R = R.transpose();
	}

	public BitStream code(BitStream in) {
		input = in;
		coded = new BitStream(in.getCodelength());
		BitSet word = null;
		for (int i = 0, j = 0; i < in.length(); i += k, j += n) {
			word = in.getRange(i, k);
			coded.set(j, n, G.mul(word));
		}
		return coded;
	}

	public BitStream decode(BitStream coded) {
		this.coded = coded;
		this.decoded = new BitStream(coded.getCodelength());
		BitSet z = null;
		BitSet word = null;
		for (int i = 0, j = 0; i < coded.length(); i += n, j += k) {
			word = coded.getRange(i, n);
			z = H.mul(word);
			if (z.cardinality() != 0) {
				correct(word, z);
			}
			decoded.set(j, k, R.mul(word));
		}
		stats.setCodingStats(input, decoded);
		return decoded;
	}

	public void correct(BitSet word, BitSet z) {
		int indexErr = 0;
		for (int j = 0; j < n - k; j++) {
			indexErr += (z.get(j)) ? (1 << j) : 0;
		}
		indexErr--;
		word.flip(indexErr);
	}

}
