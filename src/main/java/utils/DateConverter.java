package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToDate(String str) throws ParseException {
        if (str != null) {
            return dateFormat.parse(str);
        }
        return null;
    }
}
