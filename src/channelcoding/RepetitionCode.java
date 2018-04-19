package channelcoding;

import test.Statistics;
import util.BitStream;

public class RepetitionCode extends ChannelCoding {

	public RepetitionCode(int n, Statistics s) {
		super(n, 1, s);
		if (n % 2 == 0) {
			throw new RuntimeException("Fattore di ripetizione pari");
		}
	}

	public BitStream code(BitStream in) {
		input = in;
		coded = new BitStream(in.getCodelength());
		int j = 0;
		for (int i = 0; i < in.length(); i++) {
			coded.set(j, j + n, in.get(i));
			j += n;
		}
		return coded;
	}

	public BitStream decode(BitStream coded) {
		this.coded = coded;
		this.decoded = new BitStream(coded.getCodelength());
		int c;
		for (int i = 0; i < coded.length() / n; i++) {
			c = 0;
			for (int j = 0; c < n / 2 + 1 && j < n; j++) {
				if (coded.get(i * n + j)) {
					c++;
				}
			}
			if (c >= n / 2 + 1) {
				decoded.set(i);
			}
		}
		stats.setCodingStats(input, decoded);
		return decoded;
	}

}
