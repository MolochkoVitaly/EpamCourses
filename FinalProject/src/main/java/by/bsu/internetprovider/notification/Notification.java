package by.bsu.internetprovider.notification;

import com.twilio.sdk.TwilioRestException;

import javax.mail.MessagingException;

/**
 * Interface Notification ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface Notification {
    /**
     * Method send ...
     *
     * @param to of type String
     * @param text of type String
     * @throws TwilioRestException when
     * @throws MessagingException when
     */
    void send (String to, String text) throws TwilioRestException, MessagingException;
}
