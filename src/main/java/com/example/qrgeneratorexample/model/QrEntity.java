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
}
