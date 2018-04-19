package test;

import channel.Channel;
import channel.ChannelBS;
import channel.ChannelGE;
import channelcoding.*;
import util.BitStream;

public class TestCoding {
	public static void main(String[] args) {
		BitStream in = new BitStream(16);
		in.append(500);
		in.append(62);
		
		Statistics s=new Statistics();
		ChannelCoding r = new HammingCode(7,4,s);
		BitStream coded = r.code(in);
		System.out.println(in);
		System.out.println(coded);
		System.out.println(in.length());
		System.out.println(coded.length());
		System.out.println(coded.size());
		
		Channel c1 = new ChannelBS(0.0001);
		Channel c2 = new ChannelGE(0.995, 0.96, 0.0001, 0.001);
		Channel c3 = new ChannelGE(0.9999918, 0.999184, 0.00001, 0.01);
		Channel c4 = new ChannelGE(0.9999918, 0.999, 0.00001, 0.01);
		
		BitStream transmitted = c1.transmit(coded);
		BitStream decoded = r.decode(transmitted);
		System.out.println(transmitted);
		System.out.println(decoded);
		//System.out.println(decoded.length());
		//System.out.println(decoded.size());
	}
}
