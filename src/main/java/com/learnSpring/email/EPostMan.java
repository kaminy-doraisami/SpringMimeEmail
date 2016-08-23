package com.learnSpring.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * This class sends an email with an attachment using JavaMailSender
 *
 */

public class EPostMan {

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * This method sends an email message
	 * 
	 * @param from
	 *            - Email address from which the email is being sent
	 * @param to
	 *            - Email address to which the email is being sent
	 * @param subject
	 *            - Subject of the email
	 * @param body
	 *            - Body of the email
	 */
	public void sendMail(String from, String to, String subject, String body) {

		// Create the file to be sent as attachment
		FileSystemResource file = new FileSystemResource("src/main/resources/sample-file.txt");
		// Create a MimeMessage
		MimeMessage mail = mailSender.createMimeMessage();
		// Use the helper class to populate the MimeMessage
		MimeMessageHelper helper;
		try {
			/*
			 * While creating a new helper class, set the boolean flag for
			 * multipart to true, in order to facilitate sending attachments
			 */
			helper = new MimeMessageHelper(mail, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			helper.addAttachment(file.getFilename(), file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(mail);
	}
}
