package com.mail.test.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.test.dto.EmailDto;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    public JavaMailSender emailSender;

	@Override
	public String sendSimpleMessage(EmailDto emailDto) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(emailDto.getEmail()); 
        message.setSubject(emailDto.getSubject()); 
        message.setText(emailDto.getMessage());
        emailSender.send(message);
        return "email sent successfully";
	}

	@Override
	public String sendMessageWithAttachment(EmailDto emailDto) {
		MimeMessage message = emailSender.createMimeMessage();
	    try { 
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setTo(emailDto.getEmail());
	    helper.setSubject(emailDto.getSubject());
	    helper.setText(emailDto.getMessage());	         
	    
		FileSystemResource file 
	      = new FileSystemResource(new File(emailDto.getPath()));
	    helper.addAttachment(emailDto.getFileName(), file);
	    } catch (Exception e) {
	    	e.printStackTrace();
			return "Error while sending mail ..";
		}	    
	    emailSender.send(message);
	    return "Mail Sent Success!";
	}

}
