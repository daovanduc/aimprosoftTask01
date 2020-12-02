package com.dao.aimprosoft.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static final String VALIDATION_REGEX = "[A-Za-z0-9]";
    private static final String KEY = "confirm_name";
    public static final String NUMERIC_REGEX = "[A-Za-z]";

    public boolean validateName(String name, HashMap<String, String> errors, String regex, String errorMessage) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if (!name.isEmpty() && !matcher.find()) {
            String message = errorMessage + name;
            errors.put(KEY, message);
            return false;
        }
        return true;
    }


}
