package com.ashraf.androidespressotesting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {
    public static boolean isValidEmail(String emailStr) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(emailStr);
        if(matcher.find()) return true;
        else return false;
    }
    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,20})").matcher(password);
        return matcher.matches();
    }
}
