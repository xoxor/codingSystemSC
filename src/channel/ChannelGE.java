package channel;

import util.BitStream;

//Gilber-Elliot channel
public class ChannelGE extends Channel {
	
	static enum State {GOOD, BAD};
	
	double pgg;
	double pbb;
	double pg;
	double pb; 
	State state = State.GOOD;

	public ChannelGE(){
		new ChannelGE(0.995, 0.96, 0.0001, 0.001);
	}
	
	public ChannelGE(double pgg, double pbb, double pg, double pb) {
		super();
		this.pgg = pgg;
		this.pbb = pbb;
		this.pg = pg;
		this.pb = pb;
	}


	public BitStream transmit(BitStream input) {
		in = input;
		for (int i = 0; i < input.length(); i++) {
			switch (state) {
			case GOOD:
				if(Math.random() < pg){
					in.flip(i);
				}
				state = Math.random() < pgg ? State.GOOD : State.BAD;
				break;
			case BAD:
				if(Math.random() < pb){
					in.flip(i);
				}
				state = Math.random() < pbb ? State.BAD : State.GOOD;
				break;
			}
		}
		return in;
	}
}
