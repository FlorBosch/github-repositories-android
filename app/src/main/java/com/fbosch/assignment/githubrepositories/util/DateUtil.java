package com.fbosch.assignment.githubrepositories.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String parseDate(String date) {
        return convertToOtherFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", "dd/MM/yyyy", date);
    }

    public static String convertToOtherFormat(String from, String to, String date) {
        try {
            Date newDate = new SimpleDateFormat(from, Locale.getDefault()).parse(date);
            return new SimpleDateFormat(to, Locale.getDefault()).format(newDate);
        } catch (Exception ex) {
            return "";
        }
    }
}
