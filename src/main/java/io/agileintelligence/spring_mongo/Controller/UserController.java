package io.agileintelligence.spring_mongo.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.HomesUserDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.spring_mongo.Interface.UserInterface;
import io.agileintelligence.spring_mongo.model.UserModel;


@RestController
@RequestMapping(value = "/")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserInterface userInterface;

	public UserController(UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	@GetMapping(value = "/home")
	public String home(){
		return "hyy";
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<UserModel> getAllUsers() {
		LOG.info("Getting all users.");
		return userInterface.findAll();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public UserModel addNewUsers(@RequestBody UserModel user) {
		LOG.info("Saving user.");
		return userInterface.save(user);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public Optional<UserModel> getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		return userInterface.findById(userId);
	}
	
	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		
		UserModel sModel = userInterface.findFirst(userId);
		
//		UserModel user = (UserModel)userInterface.findOne(userInterface.findById())));
		
		if (sModel != null) {
			return sModel.getUserSettings();
		} else {
			return "User not found.";
		}
	}
	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		Optional<UserModel> user = userInterface.findById(userId);
		if (user != null) {
//			return user.getUserSettings().get(key);
			return "present";
		} else {
			return "User not found.";
		}
	}
	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		Optional<UserModel> user = userInterface.findById(userId);
		if (user != null) {
//			user.getUserSettings().put(key, value);
//			userInterface.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
}