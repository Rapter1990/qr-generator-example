package com.example.qrgeneratorexample.payload.request;

import com.example.qrgeneratorexample.utils.annotation.Color;
import com.example.qrgeneratorexample.utils.annotation.ImageSize;
import com.example.qrgeneratorexample.utils.annotation.TextLength;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateQrRequest {

    @NotBlank
    @TextLength
    private String text;

    @NotBlank
    @ImageSize
    private String size;

    @NotBlank
    @Color
    private String color;

    @NotBlank
    @Color
    private String backgroundColor;
}
