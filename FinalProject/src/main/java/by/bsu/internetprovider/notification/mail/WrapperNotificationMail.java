package by.bsu.internetprovider.notification.mail;

import by.bsu.internetprovider.notification.Notification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Class WrapperNotificationMail ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class WrapperNotificationMail implements Notification {
    /** Field username  */
    private final String username = "vitalymolochko@gmail.com";

    /** Field password  */
    private final String password = "my_password";

    /** Field session  */
    private Session session;

    /**
     * Constructor WrapperNotificationMail creates a new WrapperNotificationMail instance.
     */
    public WrapperNotificationMail() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.debug", "true");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
    /**
     * Method send ...
     *
     * @param to of type String
     * @param text of type String
     * @throws MessagingException when
     * @see Notification#send(String, String)
     */
    public void send(String to, String text) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject("EpamTelecom");
        message.setText(text);

        Transport.send(message);
    }
}
