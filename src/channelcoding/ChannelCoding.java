package channelcoding;

import test.Statistics;
import util.BitStream;

public abstract class ChannelCoding {
	BitStream input;
	BitStream coded;
	BitStream decoded;
	Statistics stats;
	int n;
	int k;

	public ChannelCoding(int n, int k, Statistics s) {
		this.n = n;
		this.k = k;
		this.stats = s;
	}

	public abstract BitStream code(BitStream in);

	public abstract BitStream decode(BitStream coded);

	public int getBufferSize() {
		return n;
	}

}
