//package com.bpcl.util;
//
//import java.io.IOException;
//import java.net.InetAddress;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class IpPing {
//	@Value("${kafkaIp}")
//	private static String ipAddress;
//	
//	public static String ipPing() throws IOException {
//		InetAddress geek = InetAddress.getByName(ipAddress);
//
//		if (geek.isReachable(5000)) {
//			System.out.println("success");
//			return "success";
//		} else {
//			System.out.println("fail");
//			return "fail";
//		}
//
//	}
//}
