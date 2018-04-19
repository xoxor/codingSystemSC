package channel;

import util.BitStream;

public abstract class Channel {

	BitStream in;
	BitStream out;

	public abstract BitStream transmit(BitStream input);

}
