package me.mattlogan.stravaflow.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatStravaDate(String stravaDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = sdf.parse(stravaDate);
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
