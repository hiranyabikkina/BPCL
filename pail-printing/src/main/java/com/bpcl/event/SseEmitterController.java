
package com.bpcl.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.bpcl.service.PrinterStateService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author s.k.r.subramanyam reddy guide by hiranya
 *
 */

@Controller
public class SseEmitterController {

	@Autowired
	private PrinterStateService printerStateService;
	private ExecutorService nonBlockingService = Executors.newCachedThreadPool();

	

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/sse")
	public SseEmitter handleSse() {
		SseEmitter emitter = new SseEmitter();
		SseEventBuilder eventBuilder = SseEmitter.event();
		nonBlockingService.execute(() -> {
			try {
				emitter.send(eventBuilder
						.data(" " + objectMapper.writeValueAsString(printerStateService.getInfoByUuid("9000")) + "\n\n")
						.name("dataSet-created").id("123456").reconnectTime(300)

				);

				emitter.complete();
			} catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		});
		return emitter;
	}



	@GetMapping("/printerStatus")
	public String indexPageReturn() {

		return "printerStatus";
	}
}
