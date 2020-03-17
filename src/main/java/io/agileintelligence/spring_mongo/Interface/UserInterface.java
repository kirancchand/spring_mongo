package io.agileintelligence.spring_mongo.Interface;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.spring_mongo.model.UserModel;


@Repository
public interface UserInterface extends MongoRepository<UserModel, String> {

	UserModel findFirst(String userId);
	
}