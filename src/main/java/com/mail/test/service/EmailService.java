package com.mail.test.service;

import com.mail.test.dto.EmailDto;

public interface EmailService {

	public String sendSimpleMessage(EmailDto emailDto);
		
	public String sendMessageWithAttachment(EmailDto emailDto);
}
