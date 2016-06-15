package function;

import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.net.ssl.SSLSocketFactory;

/**
 * Session Bean implementation class MailSenderBean
 */
@Stateless
@LocalBean
public class MailSenderBean {

	/**
	 * Default constructor.
	 */
	public MailSenderBean() {

	}

	public void sendEmail(String fromEmail, String userName, String password, String toEmail, String subject,
			String message) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(true);

			Message mailMessage = new MimeMessage(mailSession);

//			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(message, "text/html");
			mailMessage.setSubject(subject);
			
			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", userName, password);
			
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
			transport.close();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
