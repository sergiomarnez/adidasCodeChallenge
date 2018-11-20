package com.adidas.email.service;

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
import com.adidas.email.service.model.Email;



@RestController
@RequestMapping(value = "/email")
public class EmailServiceController {

	Logger logger = LoggerFactory.getLogger(EmailServiceController.class);
	
	@Autowired
	private final AddressService service;

	@Autowired
	public EmailServiceController(AddressService service) {
		this.service = service;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping() throws Exception {

		String serverAddress = service.getServerAddress();
		logger.info((new Date()).toString()+" - Pinging");
		return new StringBuilder().append("Service IP address: ").append(serverAddress).toString();

	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<String> sendEmail(@Valid @RequestBody Email email) {


		logger.info((new Date()).toString()+" - Sending email to :"+email.getReceiver());

		return new ResponseEntity(HttpStatus.CREATED);
	}

}

