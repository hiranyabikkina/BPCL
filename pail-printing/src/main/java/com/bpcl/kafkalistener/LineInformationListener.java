//package com.bpcl.kafkalistener;
//
//import java.net.SocketException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import com.bpcl.dto.LineInformation;
//import com.bpcl.service.LineInformationService;
//import com.bpcl.util.MacId;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class LineInformationListener {
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Autowired
//	private LineInformationService lineInformationService;
//
//	@KafkaListener(topics = "hwEquipments")
//	public void kafkaListerner(String message) throws JsonMappingException, JsonProcessingException, SocketException {
//	System.out.println(message);
//		LineInformation lineInformation = objectMapper.readValue(message, LineInformation.class);
//		if(lineInformation!=null)
//		{
//		if(lineInformation.getMacId().equalsIgnoreCase(MacId.macId()))
//		{
//			lineInformationService.insert(lineInformation);
//			
//		}
//		
//		
//		}
//	}
//}
