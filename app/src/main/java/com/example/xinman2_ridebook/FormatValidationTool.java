package com.example.xinman2_ridebook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

/*
    FormatValidationTool
    - Main purpose: validate date and date format(yyyy-mm-dd)
        validate time and time format(hh:mm)
        validate double format(2 decimal places)
 */

public class FormatValidationTool {

    public static boolean dateValidator(String date) {
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        boolean ifDateValid = datePattern.matcher(date).matches();
        if (!ifDateValid) {
            return false;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        format.setLenient(false);
        try {
            format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;

    }



    public static boolean timeValidator(String time) {
        Pattern datePattern = Pattern.compile("\\d{2}:\\d{2}");
        boolean ifDateValid = datePattern.matcher(time).matches();
        if (!ifDateValid) {
            return false;
        }

        int hour = Integer.valueOf(time.substring(0, 2));
        int minute = Integer.valueOf(time.substring(3, 5));

        if (!(0 <= hour && hour <= 23)) {
            return false;
        }
        if (!(0 <= minute && minute <= 59)) {
            return false;
        }

        return true;

    }

    public static String doubleValidator(String aDouble) {
        double realDouble = Double.valueOf(aDouble);
        return String.format("%.2f", realDouble);
    }



}
