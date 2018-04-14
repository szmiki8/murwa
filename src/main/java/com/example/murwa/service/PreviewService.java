package com.example.murwa.service;

import com.example.murwa.domain.UrlReduction;
import com.example.murwa.domain.UrlReductionRepository;
import org.fit.cssbox.demo.ImageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;

@Service
public class PreviewService {

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    @Autowired
    private UrlReductionRepository repository;

    private ImageRenderer renderer;

    public PreviewService() {
        renderer = new ImageRenderer();

        renderer.setWindowSize(new Dimension(WIDTH, HEIGHT), true);
        renderer.setLoadImages(true, true);
    }

    public byte[] render(String token) {
        UrlReduction urlReduction = repository.findByToken(token);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            renderer.renderURL(urlReduction.getUrl(), baos, ImageRenderer.Type.PNG);

            return baos.toByteArray();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

}
