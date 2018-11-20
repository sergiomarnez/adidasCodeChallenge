package com.adidas.challenge;

import java.util.Date;

import org.springframework.stereotype.Service;



@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	public String getEmail() throws Exception {

		final String email = "sergiomarnez@gmail.com";

		return email;

	}
}



