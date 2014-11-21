package com.acn.packet;


import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacketHandler;

public class PacketCaptureOffline {
	static int count = 0;
	public static void main(String[] args) {
		try {
			String fname = "C:\\Users\\sjuyal\\Desktop\\ACN\\Project\\arp.pcap";
			StringBuilder errbuf = new StringBuilder();
			Pcap pcap = Pcap.openOffline(fname, errbuf);
			if (pcap == null) {
				System.err.printf("Error while opening device for capture: "
						+ errbuf.toString());
			}

			
			PcapPacketHandler<String> packetHandler = new MyPacketHandler();
			
			
			pcap.loop(Integer.MAX_VALUE, packetHandler, "");
			
			pcap.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
