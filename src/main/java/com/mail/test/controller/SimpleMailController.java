package com.mail.test.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mail.test.dto.EmailDto;

@RestController
public class SimpleMailController {
	@Autowired
	private JavaMailSender sender;

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDto emailDto) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(emailDto.getEmail());
			helper.setText(emailDto.getMessage());
			helper.setSubject(emailDto.getSubject());
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

}