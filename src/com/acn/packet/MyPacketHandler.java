package com.acn.packet;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Arp;

public class MyPacketHandler implements PcapPacketHandler<String> {

	@Override
	public void nextPacket(PcapPacket packet, String user) {
		Arp arp = new Arp();
		if(packet.hasHeader(arp)){
			try {
				ParserOffline.parser(packet.toHexdump(packet.size(), false, false, true));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
