package minsang.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageDateFormatter {

    static SimpleDateFormat aHmm = new SimpleDateFormat("a h:mm", Locale.KOREA);

    public static String aHmmToString(Date date) {
        if(date==null){
            return null;
        }
        return aHmm.format(date);
    }
}
