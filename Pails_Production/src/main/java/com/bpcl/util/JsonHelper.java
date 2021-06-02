package com.bpcl.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Pradeep
 *
 */

public class JsonHelper {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * This functionality is used for convert jsonstring to List<T>
	 * 
	 * @param <T>
	 * @param tClass
	 * @param msg
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convertToList(Class<T> tClass, String msg)
			throws JsonMappingException, JsonProcessingException {
		List<T> list = objectMapper.readValue(msg, List.class);

		return objectMapper.convertValue(list,
				objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass));
	}

}
