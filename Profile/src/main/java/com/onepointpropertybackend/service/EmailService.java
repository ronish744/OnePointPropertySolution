package com.onepointpropertybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class EmailService {

	@Autowired(required = false)
	private JavaMailSender mailSender;

	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

	public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) {
		sendMailWithAttachment(to, subject, body, new File(fileToAttach));
	}

	public void sendMailWithAttachment(String to, String subject, String body, MultipartFile fileToAttach) {
		File convFile = new File(fileToAttach.getOriginalFilename());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(convFile);
			fos.write(fileToAttach.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendMailWithAttachment(to, subject, body, convFile);
	}

	private void sendMailWithAttachment(String to, String subject, String body, File fileToAttach) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				FileSystemResource file = new FileSystemResource(fileToAttach);
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.addTo(new InternetAddress(to));
				helper.setFrom(new InternetAddress("concreate.realtor@gmail.com"));
				mimeMessage.setSubject(subject);
				helper.setText(body);
				helper.addAttachment(file.getFilename(), file);
			}
		};

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
