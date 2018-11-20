package com.adidas.challenge;

import java.util.Date;

import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adidas.challenge.model.Subscription;
import com.adidas.challenge.model.Email;
import com.adidas.challenge.model.Event;
import com.adidas.challenge.repository.SubscriptionsRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionServiceController {

	Logger logger = LoggerFactory.getLogger(SubscriptionServiceController.class);
	
	@Autowired
	private SubscriptionsRepository repository;
	private final SubscriptionService service;
	
	@Value("${api.events.service.host}")		
	private String eventApiServiceUrl;
	@Value("${api.email.service.host}")
	private String emailApiServiceUrl;

	@Autowired
	public SubscriptionServiceController(SubscriptionService service) {
		this.service = service;
	}

	@RequestMapping(value = "/hey", method = RequestMethod.GET)
	@ApiOperation(value = "Say hey and display creator email")
	public String hey() throws Exception {

		//Gestionar la entrada de datos
		String email = service.getEmail();
		return new StringBuilder().append("Hey: ").append(email).toString();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	  @ApiOperation(value = "Creates a subscription on the system",
	    notes = "Saves subscription in database, sends an email to the subscriber confirming all it's ok and create an event on the system. "
	    		+ "Also returns subscription ID created")
	public String createSubscription(@Valid @RequestBody Subscription subs) {
		
		//Database saving
		subs.setId(ObjectId.get());
		logger.info("Saving subscription "+subs.getId()+" in MongoDB database");
		repository.save(subs);
		
		//Event creation
		Event event = new Event(subs.getId(),"Adidas Subscription Event",new Date());		
		RestTemplate restTemplate = new RestTemplate();		
		ResponseEntity<String> resultEventService = restTemplate.postForObject( eventApiServiceUrl+"/create", event, ResponseEntity.class);
		logger.info("Event creation: "+resultEventService.getStatusCodeValue());
		
		//Email sending
		Email email = new Email(subs.getEmail(),"Welcome :"+subs.getFirstName()+" Subscription confirmed ");
		restTemplate = new RestTemplate();
		ResponseEntity<String> resultEmailService = restTemplate.postForObject( emailApiServiceUrl+"send", email, ResponseEntity.class);
		logger.info("Sending email to : "+subs.getEmail()+" "+resultEventService.getStatusCodeValue());

		
		return subs.getId();
	}

}
