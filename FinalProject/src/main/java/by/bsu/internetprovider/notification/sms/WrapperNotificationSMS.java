package by.bsu.internetprovider.notification.sms;

import by.bsu.internetprovider.notification.Notification;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


/**
 * Class WrapperNotificationSMS ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class WrapperNotificationSMS implements Notification {
    /** Field ACCOUNT_SID  */
    public static final String ACCOUNT_SID = "AC02f52b170b74df243c5b9c406a04b4b9";

    /** Field AUTH_TOKEN  */
    public static final String AUTH_TOKEN = "a8bbc9273f4cfe682c49055a43f18640";

    /**
     * Constructor WrapperNotificationSMS creates a new WrapperNotificationSMS instance.
     */
    public WrapperNotificationSMS() {}

    /**
     * Method send ...
     *
     * @param phoneNumber of type String
     * @param text of type String
     * @throws TwilioRestException when
     * @see Notification#send(String, String)
     */
    public void send(String phoneNumber, String text) throws TwilioRestException {
        if (!StringUtils.isBlank(phoneNumber)) {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
            Account account = client.getAccount();
            MessageFactory messageFactory = account.getMessageFactory();
            List<NameValuePair> messageParams = new ArrayList<>();
            messageParams.add(new BasicNameValuePair("To", phoneNumber));
            //messageParams.add(new BasicNameValuePair("From", "+1 415 723 4000"));
            messageParams.add(new BasicNameValuePair("From", "+15005550006"));
            messageParams.add(new BasicNameValuePair("Body", text));
            Message message = messageFactory.create(messageParams);
            System.out.println(message.getSid());
        }
    }
}
