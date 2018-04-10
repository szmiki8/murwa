package com.example.murwa.web;

import com.example.murwa.domain.UrlReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UrlReductionCreateRequestValidator implements Validator {

    @Autowired
    private UrlReductionRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UrlReductionCreateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "field.required");

        UrlReductionCreateRequest createRequest = (UrlReductionCreateRequest) o;

        if (createRequest.getToken() != null && repository.findByToken(createRequest.getToken()) != null) {
            errors.rejectValue("token", "field.unique");
        }
    }

}
