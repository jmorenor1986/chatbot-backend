package co.com.santander.chatbot.domain.common.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

    private DateUtilities() {
    }

    public static Date sumMinutesToDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

}
