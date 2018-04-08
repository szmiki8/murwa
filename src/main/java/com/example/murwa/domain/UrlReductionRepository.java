package com.example.murwa.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlReductionRepository extends JpaRepository<UrlReduction, Long> {

    UrlReduction findByToken(String token);

}