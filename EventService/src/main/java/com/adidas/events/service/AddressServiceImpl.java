package com.adidas.events.service;

import org.springframework.stereotype.Service;
import java.net.InetAddress;

@Service
public class AddressServiceImpl implements AddressService {

	public String getServerAddress() throws Exception {

		final String serverAddress = InetAddress.getLocalHost().getHostAddress();

		return serverAddress;

	}

}
