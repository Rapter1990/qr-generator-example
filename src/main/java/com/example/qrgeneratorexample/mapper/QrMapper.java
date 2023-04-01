package com.example.qrgeneratorexample.mapper;

import com.example.qrgeneratorexample.dto.CreateQrRequestDTO;
import com.example.qrgeneratorexample.dto.QrResponseDTO;
import com.example.qrgeneratorexample.model.QrEntity;
import com.example.qrgeneratorexample.payload.request.CreateQrRequest;
import com.example.qrgeneratorexample.payload.response.QrResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class QrMapper {

    public static CreateQrRequestDTO mapCreateQrRequestToCreateQrRequestDTO(CreateQrRequest request, MultipartFile imageFile) throws IOException {
            return CreateQrRequestDTO.builder()
                    .text(request.getText())
                    .color(request.getColor())
                    .backgroundColor(request.getBackgroundColor())
                    .size(request.getSize())
                    .image(imageFile!=null ? imageFile.getBytes() :  null)
                    .build();
    }

    public static QrResponse mapCreateQrResponseDTOToQrResponse(QrResponseDTO dto) {
        return QrResponse.builder()
                .body(dto.getBody())
                .build();
    }

    public static QrEntity mapCreateQrRequestDTOToQrEntity(CreateQrRequestDTO request) {
        return QrEntity.builder()
                .text(request.getText())
                .color(request.getColor())
                .backgroundColor(request.getBackgroundColor())
                .size(request.getSize())
                .image(request.getImage())
                .build();
    }
}
