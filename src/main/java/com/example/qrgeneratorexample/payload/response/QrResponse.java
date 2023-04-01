package com.example.qrgeneratorexample.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QrResponse {
    private byte[] body;
}
