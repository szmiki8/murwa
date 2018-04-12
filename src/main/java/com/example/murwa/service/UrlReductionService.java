package com.example.murwa.service;

import com.example.murwa.domain.UrlReduction;
import com.example.murwa.domain.UrlReductionRepository;
import com.example.murwa.reducer.UrlReducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UrlReductionService {

    @Autowired
    private UrlReducer urlReducer;

    @Autowired
    private UrlReductionRepository repository;

    public UrlReduction findByToken(String token) {
        UrlReduction urlReduction = repository.findByToken(token);

        if (urlReduction == null) {
            throw new NotFoundException();
        } else {
            return urlReduction;
        }
    }

    public List<UrlReduction> findAll() {
        return repository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public UrlReduction create(String url, String preferredToken) {
        UrlReduction urlReduction = new UrlReduction(url);
        repository.save(urlReduction);

        if (preferredToken != null) {
            urlReduction.setToken(preferredToken);
        } else {
            String token = urlReducer.reduce(urlReduction.getId());
            urlReduction.setToken(token);
        }

        repository.save(urlReduction);

        return urlReduction;
    }

    public void removeAll() {
        repository.deleteAllInBatch();
    }

}
