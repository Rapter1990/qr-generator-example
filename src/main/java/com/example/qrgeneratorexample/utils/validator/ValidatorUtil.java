package com.example.qrgeneratorexample.utils.validator;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.regex.Pattern;

@Slf4j
public class ValidatorUtil {

    public static int parseColorHexValueToInteger(String color) {

        log.info("ValidatorUtil | parseColorHexValueToInteger is working");

        if (!validColor(color)) {
            log.info("ValidatorUtil | parseColorHexValueToInteger | Invalid color value");
            throw new IllegalArgumentException("Invalid color value");
        }

        log.info("ValidatorUtil | parseColorHexValueToInteger | color hex : " + "FF" + color.substring(1));
        log.info("ValidatorUtil | parseColorHexValueToInteger | color : " + Integer.parseInt(color.replaceFirst("#", ""), 16));
        return Integer.parseUnsignedInt("FF" + color.substring(1), 16);
    }

    public static boolean validImageSize(String size){

        log.info("ValidatorUtil | validImageSize is working");

        if(size.equals("small") || size.equals("medium") || size.equals("large")){
            log.info("ValidatorUtil | validImageSize | returning true");
            return true;
        }

        log.info("ValidatorUtil | validImageSize | returning false");
        return false;
    }

    public static boolean validColor(String color){

        log.info("ValidatorUtil | validColor is working");

        // Only Expecting 6 Digits Hex Code
        if (color == null || !color.startsWith("#") || color.length() != 7) {
            log.info("ValidatorUtil | validColor | returning false");
            return false;
        }

        log.info("ValidatorUtil | validColor | returning validColor");
        return Pattern.compile("[a-fA-F0-9]{6}").matcher(color.substring(1)).matches();
    }

    public static boolean validTextLength(Integer textSize){

        log.info("ValidatorUtil | validTextLength is working");

        if(textSize > 1 && textSize < 50){
            log.info("ValidatorUtil | validTextLength | returning true");
            return true;
        }

        log.info("ValidatorUtil | validTextLength | returning false");
        return false;
    }
}
