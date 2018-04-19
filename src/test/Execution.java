package test;

import java.util.LinkedList;

import channel.Channel;
import channel.ChannelBS;
import channel.ChannelGE;
import channelcoding.ChannelCoding;
import channelcoding.HammingCode;
import channelcoding.RepetitionCode;
import sourcecoding.*;
import util.BitStream;

public class Execution {

	// input
	String fileName;
	SourceCoding compressor;
	ChannelCoding[] ccoding;
	Channel c;
	int stage;
	double[] parError;
	public Statistics stats;

	public static Execution build(String fileName, int sourceCoding, LinkedList<Integer> channels, double[] parError) {
		int[] ch = new int[channels.size()];
		for (int i = 0; i < ch.length; i++) {
			ch[i] = channels.get(i);
		}
		return new Execution(fileName, sourceCoding, ch, parError);
	}

	public Execution(String fileName, int sourceCoding, int[] channels, double[] parError) {
		stats = new Statistics();
		this.fileName = fileName;
		if (sourceCoding == 1) {
			compressor = new Lzw(stats);
		} else if (sourceCoding == 2) {
			compressor = new Lzmw(stats);
		} else {
			new RuntimeException("Errore parametro codifica sorgente");
		}

		this.stage = channels.length / 2;
		ccoding = new ChannelCoding[stage];
		for (int i = 0; i < stage; i++) {
			if (channels[i * 2 + 1] == 1) {
				ccoding[i] = new RepetitionCode(channels[i * 2], stats);
			} else {
				ccoding[i] = new HammingCode(channels[i * 2], channels[i * 2 + 1], stats);
			}
		}

		if (parError.length == 1) {
			c = new ChannelBS(parError[0]);
		} else if (parError.length == 4) {
			c = new ChannelGE(parError[0], parError[1], parError[2], parError[3]);
		} else {
			new RuntimeException("Errore parametro canale.");
		}
	}

	public void execute() {
		//System.out.println(fileName);
		long start = System.currentTimeMillis();
		BitStream compressed = compressor.compress(fileName, true);

		long startc = System.currentTimeMillis();
		BitStream coded = new BitStream(compressed);
		for (int i = 0; i < stage; i++) {
			coded = ccoding[i].code(coded);
		}

		BitStream transmitted = c.transmit(coded);

		BitStream decoded = new BitStream(transmitted);
		for (int i = stage - 1; i >= 0; i--) {
			decoded = ccoding[i].decode(decoded);
		}
		long endc = System.currentTimeMillis();
		// System.out.println(decoded.getCodelength());

		int err = compressed.equals(decoded);
		if (err == 0) {
			compressor.decompress(decoded);
		} else {
			//System.out.println("Impossibile decomprimere.");
		}

		long end = System.currentTimeMillis();

		stats.setTimeCoding(startc, endc);
		stats.setTime(start, end);

		// stats.printStat();

		// System.out.println(compressor.decompress(decoded));
	}
	
	public String getStat(){
		return stats.getStat();
	}

}
