package com.mail.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mail.test.dto.EmailDto;
import com.mail.test.service.EmailService;

@RestController
public class SimpleMailController {
	@Autowired
	private EmailService emailService;

//	@PostMapping("/sendMail")
//	public String sendMail(@RequestBody EmailDto emailDto) {
//		MimeMessage message = sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//
//		try {
//			helper.setTo(emailDto.getEmail());
//			helper.setText(emailDto.getMessage());
//			helper.setSubject(emailDto.getSubject());
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return "Error while sending mail ..";
//		}
//		sender.send(message);
//		return "Mail Sent Success!";
//	}
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDto emailDto) {
		return emailService.sendSimpleMessage(emailDto);
	}
	
	@PostMapping("/sendAtachment")
	public String sendAtachment(@RequestBody EmailDto emailDto) {
		return emailService.sendMessageWithAttachment(emailDto);
	}

}