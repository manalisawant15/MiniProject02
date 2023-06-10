/**
 * 
 */
package com.ms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

/**
 * @author Sawant
 *
 */
@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to, String subject,String body) {
		
		boolean isSent = false;
		
		try {
			
			MimeMessage mimeMessage =  mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			isSent = true;
			
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	
}
