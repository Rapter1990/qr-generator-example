package com.example.qrgeneratorexample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateQrRequestDTO {
    private String text;
    private String size;
    private String color;
    private String backgroundColor;
    private byte[] image;
}
