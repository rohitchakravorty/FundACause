package com.stackroute.fundacause.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


import com.stackroute.fundacause.model.User;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
public class MailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendMail(User user) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(user.getModel());
		String html = templateEngine.process("email-template", context);
		helper.setTo(user.getEmail());
		helper.setText(html, true);
		helper.setSubject("Thank you for donating");
		helper.setFrom("rohit.rou@gmail.com");
		emailSender.send(message);
	}
}
