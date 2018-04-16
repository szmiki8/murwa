package com.example.murwa.web;

import com.example.murwa.domain.UrlReduction;
import com.example.murwa.service.PreviewService;
import com.example.murwa.service.UrlReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
@RequestMapping("/r")
public class RedirectController {

    @Autowired
    private UrlReductionService urlReductionService;

    @Autowired
    private PreviewService previewService;

    @GetMapping("/{token}")
    public String redirect(@PathVariable String token, Model model) {
        UrlReduction urlReduction = urlReductionService.findByToken(token);

        if (urlReduction == null) {
            throw new RuntimeException();
        }

        byte[] previewBytes = previewService.render(urlReduction.getToken());
        String encodedPreviewBytes = Base64.getEncoder().encodeToString(previewBytes);

        model.addAttribute("url", urlReduction.getUrl());
        model.addAttribute("preview", encodedPreviewBytes);

        return "redirect";
    }

}
