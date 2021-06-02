package com.bpcl.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MacId {
	public static String macId() throws SocketException {
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		String[] hexadecimalFormat = null;
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface ni = networkInterfaces.nextElement();
			byte[] hardwareAddress = ni.getHardwareAddress();

			if (hardwareAddress != null) {
				hexadecimalFormat = new String[hardwareAddress.length];
				for (int i = 0; i < hardwareAddress.length; i++) {
					hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
//					System.out.println(i);
				}

			}

		}

		return String.join(":", hexadecimalFormat);
	}

}
