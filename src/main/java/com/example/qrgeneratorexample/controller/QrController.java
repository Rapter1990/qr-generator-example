package com.example.qrgeneratorexample.controller;

import com.example.qrgeneratorexample.mapper.QrMapper;
import com.example.qrgeneratorexample.payload.request.CreateQrRequest;
import com.example.qrgeneratorexample.service.QrService;
import com.example.qrgeneratorexample.utils.annotation.ColorConstraint;
import com.example.qrgeneratorexample.utils.annotation.ImageSizeConstraint;
import com.example.qrgeneratorexample.utils.annotation.TextLengthConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/qr-generator")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "QR Generator API v1", description = "QR Generator API to generate QR code with image or without image")
public class QrController {

    private final QrService qrService;

    @Operation(
            method = "POST",
            summary = "Generate qr code",
            description = "generate QR code with image or without image",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "QR Code generated",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_PNG_VALUE,
                                    schema = @Schema(type = "string", format = "binary")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request Error. Not generate QR Code",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> createQrCode(@RequestParam @TextLengthConstraint @NotBlank String text,
                                               @RequestParam @ImageSizeConstraint @NotBlank String size,
                                               @RequestParam @ColorConstraint @NotBlank String color,
                                               @RequestParam @ColorConstraint @NotBlank String backgroundColor,
                                               @RequestParam(required = false) MultipartFile imageFile) throws IOException {

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
