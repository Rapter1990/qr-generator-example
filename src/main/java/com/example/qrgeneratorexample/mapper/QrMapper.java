package com.example.qrgeneratorexample.mapper;

import com.example.qrgeneratorexample.dto.CreateQrRequestDTO;
import com.example.qrgeneratorexample.dto.QrResponseDTO;
import com.example.qrgeneratorexample.payload.request.CreateQrRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class QrMapper {

    public static CreateQrRequestDTO mapCreateQrRequestToCreateQrRequestDTO(CreateQrRequest request, MultipartFile imageFile) throws IOException {
            return CreateQrRequestDTO.builder()
                    .text(request.getText())
                    .color(request.getColor())
                    .backgroundColor(request.getBackgroundColor())
                    .size(request.getSize())
                    .image(imageFile.getBytes())
                    .build();
    }

    public static QrResponseDTO mapCreateQrResponseDTOToCreateQrResponse(QrResponseDTO dto) {
        return QrResponseDTO.builder()
                .body(dto.getBody())
                .build();
    }
}
