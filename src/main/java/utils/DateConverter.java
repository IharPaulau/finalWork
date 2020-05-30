package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToDate(String str) throws ParseException {
        if (str != null) {
            Date date = dateFormat.parse(str);
            return date;
        }
        return null;
    }
}
