package ro.vladutit.Don.t.forget.v2.config;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.service.ItemService;
import ro.vladutit.Don.t.forget.v2.service.UserService;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private final String datePattern ="yyyy-MM-dd";
    private final SimpleDateFormat simpleDate = new SimpleDateFormat(datePattern);

    //Execute once a day at 00:00 am
    @Scheduled(cron = "0 0 0 * * ?")
    public void notifyExpiredItems() {
        final String startDate = simpleDate.format(new Date());
        final String endDate = LocalDate.parse(simpleDate.format(new Date())).plusDays(5).toString();

        //Get all items who expire between startDate and endDate
        final List<Item> items = itemService.getExpireItems(startDate, endDate);

        String prevUser = null; //Previous user from the list
        String content = null; //Content for email

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            //If is the first item or is another user
            if(i == 0 || prevUser.compareTo(item.user.getEmail()) != 0) {
                prevUser = item.user.getEmail();
                content = "<p>Hey, you have some items that expire today or soon: </p>";
                content += "<br>- <b>" + item.getName() + "</b> expires" + isExpiringToday(startDate, item);
            } else {
                content += "<br>- <b>" + item.getName() + "</b> expires" + isExpiringToday(startDate, item);
            }

            //If is the last user
            if (i == items.size()-1) {
                try {
                    userService.sendNotificationEmail(prevUser, content, "ALERT!");
                } catch (UnsupportedEncodingException | MessagingException e) {
                    e.printStackTrace();
                }
            } else {
                //If is the last item from the actual user
                if (prevUser.compareTo(items.get(i+1).user.getEmail()) != 0) {
                    try {
                        userService.sendNotificationEmail(prevUser, content, "ALERT!");
                    } catch (UnsupportedEncodingException | MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    private String isExpiringToday(String startDate, Item item) {
        if (startDate.equals(item.getExpirationDate())) {
            return  "<a style=\"color:red;\"> TODAY!</a></p>";
        } else {
            return  " on <a style=\"color:red;\">" + item.getExpirationDate() + "! </a></p>";
        }
    }
}
