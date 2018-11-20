package com.adidas.challenge.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adidas.challenge.model.Subscription;

public interface SubscriptionsRepository extends MongoRepository<Subscription, String> {

	Optional<Subscription> findById(Integer id);
	
}
