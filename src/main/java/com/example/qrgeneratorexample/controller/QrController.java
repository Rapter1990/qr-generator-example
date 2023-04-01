package com.example.qrgeneratorexample.controller;

import com.example.qrgeneratorexample.mapper.QrMapper;
import com.example.qrgeneratorexample.payload.request.CreateQrRequest;
import com.example.qrgeneratorexample.service.QrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/qr-generator")
@RequiredArgsConstructor
@Slf4j
public class QrController {

    private final QrService qrService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> createQrCode(@RequestPart String text,
                                               @RequestPart String size,
                                               @RequestPart String color,
                                               @RequestPart String backgroundColor,
                                               @RequestPart(required = false) MultipartFile imageFile) throws IOException {

        CreateQrRequest request = CreateQrRequest.builder()
                .text(text)
                .backgroundColor(backgroundColor)
                .color(color)
                .size(size)
                .build();

        log.info("QrController | createQrCode is working");

        var createQrRequestDTO = QrMapper.mapCreateQrRequestToCreateQrRequestDTO(request, imageFile);

        var qrResponseDTO = qrService.createQrCode(createQrRequestDTO);

        var imageQrCode = QrMapper.mapCreateQrResponseDTOToQrResponse(qrResponseDTO).getBody();

        return new ResponseEntity<>(imageQrCode, HttpStatus.CREATED);

    }
}
