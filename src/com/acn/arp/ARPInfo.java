package com.acn.arp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ARPInfo {
	static Map<Integer, String> hardwareType = new HashMap<Integer, String>();
	static Map<Integer, String> opCode = new HashMap<Integer, String>();
	static Map<Integer, String> protocolType = new HashMap<Integer, String>();

	
	public static String getHardwareType(int val) {
		if(hardwareType.isEmpty()){
			try {
				String path = System.getProperty("user.dir");
				BufferedReader in = new BufferedReader(new FileReader(path + "\\Resources\\HardwareType"));
				String line = "";
				while ((line = in.readLine()) != null) {
					String parts[] = line.split("\t");
					hardwareType.put(Integer.parseInt(parts[0]), parts[1]);
				}
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if((val >= 38 && val <= 255) || (val >= 257 && val <= 65534))
			return "Unassigned";
		return hardwareType.get(val);
	}
	
	public static String getProtocolType(int val) {
		if(protocolType.isEmpty()){
			try {
				String path = System.getProperty("user.dir");
				BufferedReader in = new BufferedReader(new FileReader(path + "\\Resources\\ProtocolType"));
				String line = "";
				while ((line = in.readLine()) != null) {
					String parts[] = line.split("\t");
					protocolType.put(Integer.parseInt(parts[0]), parts[1]);
				}
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(protocolType.get(val)==null)
			return "Unassigned";
		return protocolType.get(val);
	}
	
	
	public static String getOpCode(int val) {
		if(opCode.isEmpty()){
			try {
				String path = System.getProperty("user.dir");
				BufferedReader in = new BufferedReader(new FileReader(path + "\\Resources\\OpCode"));
				String line = "";
				while ((line = in.readLine()) != null) {
					String parts[] = line.split("\t");
					opCode.put(Integer.parseInt(parts[0]), parts[1]);
				}
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if((val >= 26 && val <= 65534))
			return "Unassigned";
		return opCode.get(val);
	}
}
