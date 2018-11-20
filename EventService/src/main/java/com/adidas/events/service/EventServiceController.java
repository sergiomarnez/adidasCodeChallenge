package com.adidas.events.service;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.adidas.events.service.model.Event;

@RestController
@RequestMapping(value = "/event")
public class EventServiceController {
	
	Logger logger = LoggerFactory.getLogger(EventServiceController.class);

	@Autowired
	private final AddressService service;

	@Autowired
	public EventServiceController(AddressService service) {
		this.service = service;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping() throws Exception {

		String serverAddress = service.getServerAddress();
		return new StringBuilder().append("Service IP address: ").append(serverAddress).toString();

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createEvent(@Valid @RequestBody Event event) {

		logger.info((new Date()).toString()+" - Creating event :"+event.getDescription());
		return new ResponseEntity(HttpStatus.CREATED);
	}

}

