package com.example.qrgeneratorexample.controller;

import com.example.qrgeneratorexample.base.BaseControllerTest;
import com.example.qrgeneratorexample.data.DummyData;
import com.example.qrgeneratorexample.service.QrService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QrControllerTest extends BaseControllerTest {

    private static final String API_URL = "/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    QrService qrService;


    @SneakyThrows
    @Test
    void shouldCreateQrCodeWithImage() {

        // when
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", "java-original-wordmark.png", MediaType.IMAGE_PNG_VALUE, "java-original-wordmark".getBytes());
        var createQrCodeRequest = DummyData.getDummyCreateQrRequest();
        var qrResponseDTO = DummyData.getDummyQrResponseDTO(imageFile.getBytes());
        var createQrRequestDTO =DummyData.getDummyCreateQrRequestDTOWithImage(createQrCodeRequest,imageFile);

        // when
        when(qrService.createQrCode(createQrRequestDTO)).thenReturn(qrResponseDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_URL + "/qr-generator")
                        .file(imageFile)
                        .param("text", createQrCodeRequest.getText())
                        .param("size", createQrCodeRequest.getSize())
                        .param("color", createQrCodeRequest.getColor())
                        .param("backgroundColor", createQrCodeRequest.getBackgroundColor()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.IMAGE_PNG_VALUE));

    }

    @SneakyThrows
    @Test
    void shouldCreateQrCodeWithoutImage() {
        // when
        var createQrCodeRequest = DummyData.getDummyCreateQrRequest();
        var qrResponseDTO = DummyData.getDummyQrResponseDTO(null);
        var createQrRequestDTO =DummyData.getDummyCreateQrRequestDTOWithoutImage(createQrCodeRequest);

        // when
        when(qrService.createQrCode(createQrRequestDTO)).thenReturn(qrResponseDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_URL + "/qr-generator")
                        .param("text", createQrCodeRequest.getText())
                        .param("size", createQrCodeRequest.getSize())
                        .param("color", createQrCodeRequest.getColor())
                        .param("backgroundColor", createQrCodeRequest.getBackgroundColor()))
                .andExpect(status().isCreated());
    }
}
