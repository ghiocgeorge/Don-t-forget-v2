package ro.vladutit.Don.t.forget.v2.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.User;

@Service
public class SmsService {

    private final static String ACCOUNT_SID = "ACf79d83e1ef84454b99835c9ba127de38";
    private final static String AUTH_TOKEN = "";
    private final static String FROM_NO = "+16062577400";

    public void sendNotificationSms(User user, String smsContent) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        smsContent = "Hi " + user.getFullname() + ". " + smsContent + " Please don't reply at this number! " +
                "Thank you! The Don't forget app team.";
        Message.creator(new PhoneNumber(user.getTelephone()), new PhoneNumber(FROM_NO), smsContent).create();
    }
}
