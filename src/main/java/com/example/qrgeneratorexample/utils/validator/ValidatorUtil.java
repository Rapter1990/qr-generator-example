package com.example.qrgeneratorexample.utils.validator;

import java.util.regex.Pattern;

public class ValidatorUtil {

    public static int parseColorHexValueToInteger(String color) {
        if (!validColor(color)) {
            throw new IllegalArgumentException("Invalid color value");
        }

        return Integer.parseUnsignedInt("FF" + color.substring(1), 16);
        //return Integer.parseInt(color);
    }

    public static boolean validImageSize(String size){
        if(size.equals("small") || size.equals("medium") || size.equals("large")){
            return true;
        }
        return false;
    }

    public static boolean validColor(String color){
        // Only Expecting 6 Digits Hex Code
        if (color == null || !color.startsWith("#") || color.length() != 7) {
            return false;
        }

        return Pattern.compile("[a-fA-F0-9]{6}").matcher(color.substring(1)).matches();
    }

    public static boolean validTextLength(Integer textSize){
        if(textSize > 1 && textSize < 50){
            return true;
        }
        return false;
    }
}
