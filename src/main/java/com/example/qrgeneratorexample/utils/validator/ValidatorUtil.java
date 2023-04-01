package com.example.qrgeneratorexample.utils.validator;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.regex.Pattern;

@Slf4j
public class ValidatorUtil {

    public static int parseColorHexValueToInteger(String color) {

        log.info("QrService | parseColorHexValueToInteger is working");

        if (!validColor(color)) {
            log.info("QrService | parseColorHexValueToInteger | Invalid color value");
            throw new IllegalArgumentException("Invalid color value");
        }

        log.info("QrService | parseColorHexValueToInteger | color hex : " + "FF" + color.substring(1));
        log.info("QrService | parseColorHexValueToInteger | color : " + Integer.parseInt(color.replaceFirst("#", ""), 16));
        return Integer.parseUnsignedInt("FF" + color.substring(1), 16);
    }

    public static boolean validImageSize(String size){
        if(size.equals("small") || size.equals("medium") || size.equals("large")){
            return true;
        }
        return false;
    }

    public static boolean validColor(String color){

        log.info("QrService | validColor is working");

        // Only Expecting 6 Digits Hex Code
        if (color == null || !color.startsWith("#") || color.length() != 7) {
            log.info("QrService | validColor | returning false");
            return false;
        }

        log.info("QrService | validColor | returning validColor");
        return Pattern.compile("[a-fA-F0-9]{6}").matcher(color.substring(1)).matches();
    }

    public static boolean validTextLength(Integer textSize){

        log.info("QrService | validTextLength is working");

        if(textSize > 1 && textSize < 50){
            log.info("QrService | validTextLength | returning true");
            return true;
        }

        log.info("QrService | validTextLength | returning false");
        return false;
    }
}
