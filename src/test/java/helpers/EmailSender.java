package helpers;



import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import helpers.XMLReader;


public class EmailSender {
	
	private XMLReader reader = new XMLReader();
	String email = reader.readNode("email");
	String password = reader.readNode("password");
	
	
	public void sendMail() {
		
		Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.outlook.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        
        props.setProperty("mail.user", email);
        props.setProperty("mail.password", password);
        
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, null);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        Compressor zipFile = new Compressor();
        
        try {
        	zipFile.createZip();
			message.setFrom(new InternetAddress("belatrix-test@outlook.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reader.readNode("receptor")));
			message.setSubject("REPORTE TEST CASES");
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Envio del reporte del test");
			DataSource source = new FileDataSource("C:\\Users\\rgioia\\workspace\\CursoAutomationII\\Folder.zip");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Reporte" + ".rar");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport t = session.getTransport("smtp");
			t.connect(email, password);
			t.sendMessage(message,message.getAllRecipients());
			t.close();
			
		} catch (AddressException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}
}
