package com;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	model.Message message = new model.Message();

	String sender = "", passwprd = "";

	public String sendmail(String receiver, String subject, String body)
			throws AddressException, MessagingException, IOException {
		String sql = "SELECT mail_server AS \"MailServer\",mail_server_port AS \"MailServerPort\",mail_sender AS \"MailSender\",mail_password AS \"MailPassword\" FROM organization_master WHERE id=1";
		message.map = (Map) message.db.getRecord(sql).get(0);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", message.map.get("MailServer"));
		props.put("mail.smtp.port", message.map.get("MailServerPort"));
		sender = message.map.get("MailSender").toString();
		passwprd = message.map.get("MailPassword").toString();
		System.out.println("mail sender " + sender + " password " + passwprd);
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        sender = "noreplykamalamai@gmail.com";
//        passwprd = "kamalamai@123";
		String message = "Sent";
		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(sender, passwprd);
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender, false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			msg.setSubject(subject);
			msg.setContent(body, "text/html");
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (Exception e) {
			message = e.getMessage();
		}
		return message;

	}

}
