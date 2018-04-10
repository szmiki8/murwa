package com.example.murwa.web;

import com.example.murwa.service.UrlReductionService;
import com.example.murwa.domain.UrlReduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/url_reduction")
public class UrlReductionController {

    @Autowired
    private UrlReductionService service;

    @Autowired
    private UrlReductionCreateRequestValidator validator;

    @GetMapping
    public Collection<UrlReduction> getAll() {
        return service.findAll();
    }

    @GetMapping("/{token}")
    public UrlReduction get(@PathVariable String token) {
        return service.findByToken(token);
    }

    @PostMapping
    public UrlReduction create(@Valid @RequestBody UrlReductionCreateRequest request) {
        return service.create(request.getUrl(), request.getToken());
    }

    @DeleteMapping
    public void deleteAll() {
        service.removeAll();
    }

    @InitBinder("urlReductionCreateRequest")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}
