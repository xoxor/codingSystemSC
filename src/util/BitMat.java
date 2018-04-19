package util;

import java.util.BitSet;

public class BitMat {

	BitSet[] matrix;
	int r;
	int c;

	public BitMat(int r, int c) {
		matrix = new BitSet[c];
		this.r = r;
		this.c = c;
		for (int i = 0; i < c; i++) {
			matrix[i] = new BitSet(r);
		}
	}

	public void set(int i, int j, boolean b) {
		matrix[j].set(i, b);
	}

	public void set(int i, int j) {
		set(i, j, true);
	}
	
	public boolean get(int i, int j){
		return matrix[j].get(i);
	}

	public BitSet getRow(int r) {
		BitSet row = new BitSet();
		for (int i = 0; i < c; i++) {
			row.set(i, matrix[i].get(r));
		}
		return row;
	}

	public BitSet getCol(int c) {
		BitSet col = new BitSet();
		for (int i = 0; i < r; i++) {
			col.set(i, matrix[c].get(i));
		}
		return col;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < r; i++) {
			if (i > 0) {
				sb.append('\n');
			}
			for (int j = 0; j < c; j++) {
				int v = matrix[j].get(i) ? 1 : 0;
				sb.append(v + "\t");
			}
		}
		return sb.toString();
	}

	public BitSet mul(BitSet vettoreRiga) {
		BitSet res = new BitSet();
		BitSet temp = new BitSet();
		for (int i = 0; i < c; i++) {
			temp = this.getCol(i);
			temp.and(vettoreRiga);
			if (temp.cardinality() % 2 != 0)
				res.set(i);
		}
		return res;
	}

	public BitMat transpose(){
		BitMat t = new BitMat(c, r);
		for(int i = 0; i<r; i++){
			for(int j=0; j<c; j++){
				t.set(j, i, this.get(i, j));
			}
		}
		return t;
	}


}
