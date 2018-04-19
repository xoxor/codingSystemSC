package channel;

import util.BitStream;

public class ChannelBS extends Channel {

	double alpha;

	public ChannelBS(double alpha) {
		super();
		this.alpha = alpha;
	}

	public BitStream transmit(BitStream input) {
		in = input;
		for (int i = 0; i < input.length(); i++) {
			if(Math.random() < alpha){
				in.flip(i);
			}
		
		}
		return in;
	}
}
