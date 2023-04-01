package com.example.qrgeneratorexample.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QrResponseDTO {
    private byte[] body;
}
