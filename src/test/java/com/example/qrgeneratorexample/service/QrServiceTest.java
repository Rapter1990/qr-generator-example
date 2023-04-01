package com.example.qrgeneratorexample.service;

import com.example.qrgeneratorexample.base.BaseServiceTest;
import com.example.qrgeneratorexample.data.DummyData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QrServiceTest extends BaseServiceTest {

    @InjectMocks
    QrService qrService;

    @SneakyThrows
    @Test
    void createQrCodeWithoutImage() {
        var requestDTO = DummyData.getDummyCreateQrRequestDTO();

        var responseDTO = qrService.createQrCode(requestDTO);


        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getBody()).isNotNull().isNotEmpty();

        byte[] binaryData = responseDTO.getBody();
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(binaryData));
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

        var result = new MultiFormatReader().decode(binaryBitmap);

        assertEquals(result.getText(), requestDTO.getText());
        assertEquals(result.getBarcodeFormat(), BarcodeFormat.QR_CODE);

    }

    @SneakyThrows
    @Test
    void createQrCodeWithImage() {
        var requestDTO = DummyData.getDummyCreateQrRequestDTO();
        requestDTO.setImage(new ClassPathResource("java-original-wordmark.png").getInputStream().readAllBytes());

        var responseDTO = qrService.createQrCode(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getBody()).isNotNull().isNotEmpty();


        byte[] binaryData = responseDTO.getBody();
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(binaryData));
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

        var result = new MultiFormatReader().decode(binaryBitmap);

        assertEquals(result.getText(), requestDTO.getText());
        assertEquals(result.getBarcodeFormat(), BarcodeFormat.QR_CODE);

    }
}
