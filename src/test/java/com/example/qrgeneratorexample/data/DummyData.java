package com.example.qrgeneratorexample.data;

import com.example.qrgeneratorexample.dto.CreateQrRequestDTO;
import com.example.qrgeneratorexample.dto.QrResponseDTO;
import com.example.qrgeneratorexample.payload.request.CreateQrRequest;
import com.example.qrgeneratorexample.payload.response.QrResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class DummyData {


    public static CreateQrRequestDTO getDummyCreateQrRequestDTO() {
        return CreateQrRequestDTO.builder()
                .text("Dummy Value Text")
                .color("#0046FF")
                .backgroundColor("#64FF00")
                .size("small")
                .build();
    }

    public static QrResponseDTO getDummyQrResponseDTO(byte[] bytes) {
        return QrResponseDTO.builder()
                .body(bytes)
                .build();
    }

    public static CreateQrRequest getDummyCreateQrRequest() {
        return CreateQrRequest.builder()
                .text("Dummy Value Text")
                .color("#0046FF")
                .backgroundColor("#64FF00")
                .size("small")
                .build();
    }

    public static CreateQrRequestDTO getDummyCreateQrRequestDTOWithImage(CreateQrRequest request, MultipartFile imageFile) throws IOException {
        return CreateQrRequestDTO.builder()
                .text(request.getText())
                .color(request.getColor())
                .backgroundColor(request.getBackgroundColor())
                .size(request.getSize())
                .image(imageFile.getBytes())
                .build();
    }

    public static CreateQrRequestDTO getDummyCreateQrRequestDTOWithoutImage(CreateQrRequest request) throws IOException {
        return CreateQrRequestDTO.builder()
                .text(request.getText())
                .color(request.getColor())
                .backgroundColor(request.getBackgroundColor())
                .size(request.getSize())
                .build();
    }

    public static QrResponse getQrResponse(QrResponseDTO dto) {
        return QrResponse.builder()
                .body(dto.getBody())
                .build();
    }


}
