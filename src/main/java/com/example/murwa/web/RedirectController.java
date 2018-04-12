package com.example.murwa.web;

import com.example.murwa.domain.UrlReduction;
import com.example.murwa.service.UrlReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/r")
public class RedirectController {

    @Autowired
    private UrlReductionService service;

    @GetMapping("/{token}")
    public ResponseEntity<?> get(@PathVariable String token) {
      UrlReduction urlReduction = service.findByToken(token);

      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create(urlReduction.getUrl()));

      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
