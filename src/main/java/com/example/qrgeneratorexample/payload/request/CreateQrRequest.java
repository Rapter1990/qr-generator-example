package com.example.qrgeneratorexample.payload.request;

import com.example.qrgeneratorexample.utils.annotation.ColorConstraint;
import com.example.qrgeneratorexample.utils.annotation.ImageSizeConstraint;
import com.example.qrgeneratorexample.utils.annotation.TextLengthConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateQrRequest {

    private String text;

    private String size;

    private String color;

    private String backgroundColor;
}
