package com.example.qrgeneratorexample.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QrEntity {
    private String text;
    private String size;
    private String color;
    private String backgroundColor;
    private byte[] image;


    public int getQRSizeValue() {
        if (size.equals("small")) {
            return 200;
        } else if (size.equals("medium")) {
            return 300;
        } else { // large
            return 400;
        }
    }

    public int getImageOverlaySizeValue() {
        if (size.equals("small")) {
            return 50;
        } else if (size.equals("medium")) {
            return 75;
        } else { // large
            return 90;
        }
    }
}
