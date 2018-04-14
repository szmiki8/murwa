package com.example.murwa.web;

import com.example.murwa.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/render")
public class PreviewController {

    @Autowired
    private PreviewService service;

    @GetMapping("/{token}")
    public ResponseEntity<?> get(@PathVariable String token) {
        byte[] data = service.render(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
