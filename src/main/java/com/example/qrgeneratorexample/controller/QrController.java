package com.example.qrgeneratorexample.controller;


import com.example.qrgeneratorexample.payload.request.CreateQrRequest;
import com.example.qrgeneratorexample.service.QrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qr-generator")
@RequiredArgsConstructor
public class QrController {

    private final QrService qrService;

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> createQrCode(@RequestBody CreateQrRequest request) {
        return null;
    }
}
