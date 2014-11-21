package com.acn.packet;

import com.acn.arp.ARPInfo;

public class ParserOffline {
	public static void parser(String s) throws Exception {
		s = s.replaceAll("\\s+", " ");
		s = s.trim();
		System.out.println("-----------------------------------------ARP Frame:-------------------------------------------");
		System.out.println();
		String destmac = s.substring(0, 17);
		destmac = destmac.replace(" ", ":");
		System.out.println("Destination MAC Address--> " + destmac);

		String srcmac = s.substring(18, 35);
		srcmac = srcmac.replace(" ", ":");
		System.out.println("Source MAC Address--> " + srcmac);

		String protocoltype = s.substring(36, 41);
		if (protocoltype.equals("08 06")) {
			System.out.println("\nIt is a ARP<" + protocoltype + "> Packet");
			System.out.println("\nARP Packet Header::");

			int hardwareTypeInt = convertHexToDecimal(s.substring(42, 47));
			String hardwareTypeString = ARPInfo
					.getHardwareType(hardwareTypeInt);
			System.out.println("Hardware Type--> " + hardwareTypeString);

			int protocolTypeInt = convertHexToDecimal(s.substring(48, 53));
			String protocolTypeString = ARPInfo.getProtocolType(protocolTypeInt);
			System.out.println("Protocol Type--> " + protocolTypeString);

			int hardwareSize = convertHexToDecimal(s.substring(54, 56));
			System.out.println("Hardware Size--> " + hardwareSize);

			int protocolSize = convertHexToDecimal(s.substring(57, 59));
			System.out.println("Protocol Size--> " + protocolSize);

			int opCode = convertHexToDecimal(s.substring(60, 65));
			String opCodeString = ARPInfo.getOpCode(opCode);
			System.out.println("Opcode--> " + opCodeString);

			String senderMac = s.substring(66, 83);
			senderMac = senderMac.replace(" ", ":");
			System.out.println("Sender MAC Address--> " + senderMac);

			String senderIP = s.substring(84, 95);
			senderIP = getIPfromHex(senderIP);
			System.out.println("Sender IP Address--> " + senderIP);
			
			String targetMac = s.substring(96, 113);
			targetMac = targetMac.replace(" ", ":");
			System.out.println("Target MAC Address--> " + targetMac);

			String targetIP = s.substring(114, 125);
			targetIP = getIPfromHex(targetIP);
			System.out.println("Target IP Address--> " + targetIP);

		} else {
			System.out.println("Not a ARP Frame!!");
		}
		System.out.println();

	}

	public static int convertHexToDecimal(String Hex) {
		Hex = Hex.replace(" ", "");
		int dec = Integer.parseInt(Hex, 16);
		return dec;
	}

	public static String getIPfromHex(String Hex) {
		Hex = Hex.replace(" ", "");
		String splitString[] = new String[4];
		for (int i = 0, j = 0; i < Hex.length(); i += 2, j++) {
			splitString[j] = "" + Hex.charAt(i) + Hex.charAt(i + 1);
		}
		String IP = "";
		for (int i = 0; i < splitString.length; i++) {
			if (i == 0)
				IP = IP + convertHexToDecimal(splitString[i]);
			else
				IP = IP + "." + convertHexToDecimal(splitString[i]);
		}
		return IP;
	}
}