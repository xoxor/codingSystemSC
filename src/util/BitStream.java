package util;

import java.util.BitSet;

public class BitStream {
	int codelength;
	public BitSet word;
	int index;

	public BitStream(int codelength) {
		this.codelength = codelength;
		word = new BitSet();
		index = 0;
	}

	public BitStream(int codelength, int value) {
		this.codelength = codelength;
		word = new BitSet(codelength);
		int i = 0;
		while (value != 0) {
			if (value % 2 != 0) {
				word.set(i);
			}
			i++;
			value = value >>> 1;
		}
		index = codelength;
	}

	public BitStream (BitStream b){
		this.codelength=b.getCodelength();
		this.index=b.length();
		this.word=b.getRange(0, index);
	}
	
	public void append(int value) {
		int i = index;
		while (value != 0) {
			if (value % 2 != 0) {
				word.set(i);
			}
			i++;
			value = value >>> 1;
		}
		index += codelength;
	}

	public void set(int i) {
		set(i, i + 1, true);
	}

	public void set(int i, int j) {
		set(i, j, true);
	}

	public void set(int i, int j, boolean b) {
		word.set(i, j, b);
		if (j - 1 > index) {
			index = (int) Math.ceil((j - 1) / (double) codelength) * codelength;
		}
	}

	public void set(int ind, int dim, BitSet b) {
		for (int i = 0; i < dim; i++) {
			if (b.get(i))
				set(ind + i);
		}
	}

	public void flip(int i) {
		boolean b = !word.get(i);
		set(i, i + 1, b);
	}

	public boolean get(int i) {
		return word.get(i);
	}

	public int getInt(int pos) {
		int value = 0;
		for (int i = 0; i < codelength; i++) {
			value += (word.get(pos * codelength + i)) ? (1 << i) : 0;
		}
		return value;
	}

	public BitSet getRange(int ind, int k) {
		BitSet b = new BitSet(k);
		for (int i = 0; i < k; i++) {
			b.set(i, word.get(ind + i));
		}
		return b;
	}

	public int getCodelength() {
		return codelength;
	}

	public int length() {
		return index;
	}

	public int size() {
		return index / codelength;
	}

	public int equals(BitStream b){
		BitSet bs= b.getRange(0, length());
		bs.xor(word);
		return bs.cardinality();
	}
	
	
	public String toString() {
		char[] c = new char[length() + 2];
		c[0] = '[';
		c[length() + 1] = ']';
		for (int i = 1; i <= length(); i++) {
			if (word.get(i - 1)) {
				c[i] = '1';
				//c[length() + 1 - i] = '1';
			} else {
				c[i] = '0';
				//c[length() + 1 - i] = '0';
			}
		}
		return new String(c);
	}

	public int hashCode() {
		return word.hashCode();
	}


}
