package com.example.qrgeneratorexample.service;

import com.example.qrgeneratorexample.dto.CreateQrRequestDTO;
import com.example.qrgeneratorexample.dto.QrResponseDTO;
import com.example.qrgeneratorexample.exception.QrException;
import com.example.qrgeneratorexample.mapper.QrMapper;
import com.example.qrgeneratorexample.model.QrEntity;
import com.example.qrgeneratorexample.utils.contants.QrConstants;
import com.example.qrgeneratorexample.utils.validator.ValidatorUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class QrService {

    public QrResponseDTO createQrCode(CreateQrRequestDTO request) {

        log.info("QrService | createQrCode is working");


        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            var qrEntity = QrMapper.mapCreateQrRequestDTOToQrEntity(request);

            log.info("QrService | createQrCode | qrEntity Text : " + qrEntity.getText());

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            var hints = Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            var bitMatrix = qrCodeWriter.encode(qrEntity.getText(), BarcodeFormat.QR_CODE,
                    qrEntity.getQRSizeValue(), qrEntity.getQRSizeValue(), hints);

            log.info("QrService | createQrCode | Setting QR code colors");

            log.info("QrService | createQrCode | QR color :" + qrEntity.getColor());
            log.info("QrService | createQrCode | QR backgroundColor :" + qrEntity.getBackgroundColor());

            // Set QR code colors
            var config = new MatrixToImageConfig(ValidatorUtil.parseColorHexValueToInteger(qrEntity.getColor()),
                    ValidatorUtil.parseColorHexValueToInteger(qrEntity.getBackgroundColor()));

            var qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
            qrImage = setOverlayImage(qrEntity, qrImage);
            ImageIO.write(qrImage, QrConstants.QR_IMAGE_FORMAT, outputStream);

            byte[] bytes = outputStream.toByteArray();

            log.info("QrService | createQrCode | returning QrResponseDTO");

            return QrResponseDTO.builder()
                    .body(bytes)
                    .build();

        }catch (Exception e) {
            throw new QrException(QrConstants.QR_CREATE_EXCEPTION);
        }

    }

    private BufferedImage setOverlayImage(QrEntity qrEntity, BufferedImage qrImage) throws IOException {

        log.info("QrService | setOverlayImage is working");

        if (qrEntity.getImage() == null || qrEntity.getImage().length == 0) {
            return qrImage;
        }

        log.info("QrService | setOverlayImage |  Resizing Overlay Image");

        var size = qrEntity.getImageOverlaySizeValue();
        var resizedImage = resizeOverlayImage(qrEntity.getImage(), size, size);
        resizedImage = scaleOverlay(resizedImage, size, size);

        var deltaHeight = qrImage.getHeight() - resizedImage.getHeight();
        var deltaWidth = qrImage.getWidth() - resizedImage.getWidth();

        BufferedImage combined = new BufferedImage(qrImage.getWidth(), qrImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) combined.getGraphics();
        g2.drawImage(qrImage, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.drawImage(resizedImage, Math.round(deltaWidth / 2f), Math.round(deltaHeight / 2f), null);

        log.info("QrService | setOverlayImage | returning combined");

        return combined;
    }

    private BufferedImage resizeOverlayImage(byte[] imageData, int desiredWidth, int desiredHeight) throws IOException {

        log.info("QrService | resizeOverlayImage is working");

        var image = ImageIO.read(new ByteArrayInputStream(imageData));
        var resultingImage = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_DEFAULT);
        var outputImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        log.info("QrService | resizeOverlayImage | returning outputImage");

        return outputImage;
    }

    private BufferedImage scaleOverlay(BufferedImage overlay, int scaledWidth, int scaledHeight) {

        log.info("QrService | scaleOverlay is working");

        BufferedImage imageBuff = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imageBuff.createGraphics();
        g.drawImage(overlay.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 0, 0, new Color(0, 0, 0), null);
        g.dispose();

        log.info("QrService | resizeOverlayImage | returning imageBuff");

        return imageBuff;
    }

}
