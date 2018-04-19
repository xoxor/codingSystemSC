package test;

import util.BitStream;

public class Statistics {

	public double time;

	// statistiche compressione
	public int inputsymbols;
	public int inputbitsymbols;
	public int inputSize;
	public int outputsymbols;
	public int outputbitsymbols;
	public int outputSize;
	public double compressionratio;

	// statistiche codifica canale
	public int codingErrors;
	public double ber;
	public double timeCoding;

	public void setCompressionStats(String input, int s, BitStream output, int codelen) {
		inputsymbols = input.length();
		inputbitsymbols = s;
		inputSize = input.length() * s;
		outputsymbols = output.size();
		outputbitsymbols = codelen;
		outputSize = output.size() * codelen;
		compressionratio = (1.0 - outputSize / (double) inputSize) * 100.0;
	}

	public void setTimeCoding(double start, double end) {
		timeCoding = (end - start) / 1000.0;
	}

	public void setTime(double start, double end) {
		time = (end - start) / 1000.0;
	}

	public void setCodingStats(BitStream compressed, BitStream decoded) {
		codingErrors = compressed.equals(decoded);
		ber = codingErrors / (double) compressed.length();
	}

	public String getStat() {
		StringBuilder sb = new StringBuilder();

		sb.append("COMPRESSION INPUT");
		sb.append('\n');
		sb.append("-" + inputsymbols + " symbols");
		sb.append('\n');
		sb.append("-" + inputbitsymbols + " bit per symbol");
		sb.append('\n');
		sb.append("-" + inputSize + " bit of total size");
		sb.append('\n');
		sb.append("COMPRESSION OUTPUT");
		sb.append('\n');
		sb.append("-" + outputsymbols + " symbols");
		sb.append('\n');
		sb.append("-" + outputbitsymbols + " bit per symbol (codelength)");
		sb.append('\n');
		sb.append("-" + outputSize + " bit of total size");
		sb.append('\n');
		sb.append('\n');
		sb.append("Compression ratio: " + compressionratio + "%");
		sb.append('\n');
		sb.append("Number of decoding errors: " + codingErrors);
		sb.append('\n');
		sb.append("BER (Bit Error Ratio): " + ber);
		sb.append('\n');
		sb.append("Time end-to-end: " + time + " sec");
		sb.append('\n');
		sb.append('\n');
		if (codingErrors > 0) {
			sb.append("IMPOSSIBLE TO DECOMPRESS");
		}
		return sb.toString();
	}

	public void printStat() {
		System.out.println(getStat());
	}

}
