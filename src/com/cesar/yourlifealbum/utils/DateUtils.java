package com.cesar.yourlifealbum.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String CLASS_NAME = DateUtils.class.getSimpleName();

    // 2012-09-15T11:59:41+0200

    private static final String RFC822_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static Date dateFromRFC3339String(final String date) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(RFC822_DATE_FORMAT,
                    Locale.US);
            Date d = sdf.parse(date);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
