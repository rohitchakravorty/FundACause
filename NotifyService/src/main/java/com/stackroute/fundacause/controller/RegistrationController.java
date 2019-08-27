package com.stackroute.fundacause.controller;

import javax.mail.MessagingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.fundacause.model.User;
import com.stackroute.fundacause.service.MailService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {

	@Autowired
	private MailService notificationService;

	@Autowired
	private User user;

	@KafkaListener(topics = "Kafka_Example", group = "group_id")
	public void consume(byte[] ba)
	{
		try {
			user = (User) new ObjectMapper().readValue(ba, User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("send-mail")
	public String send() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("amount", user.getAmount());
		model.put("cause", user.getCause());
		model.put("name",user.getName());
		model.put("signature", "Fund A Cause");
		user.setModel(model);

		try {
			notificationService.sendMail(user);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
}
