package com.example.murwa.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlReductionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UrlReductionRepository repository;

    @Test
    public void testRepositoryCount() {
        entityManager.persist(new UrlReduction("9gag.com"));

        assertEquals(1, repository.count());
    }

    @Test
    public void testPersistUrlReductionWithoutToken() {
        UrlReduction urlReduction = new UrlReduction("9gag.com");
        entityManager.persist(urlReduction);

        UrlReduction persistedUrlReduction = repository.findOne(urlReduction.getId());

        assertNull(persistedUrlReduction.getToken());
    }

    @Test
    public void testPersistUrlReductionWithToken() {
        UrlReduction urlReduction = new UrlReduction("9gag.com", "token");
        entityManager.persist(urlReduction);

        UrlReduction persistedUrlReduction = repository.findOne(urlReduction.getId());

        assertNotNull(persistedUrlReduction.getToken());
    }

    @Test
    public void testFindByToken() {
        UrlReduction expected = new UrlReduction("9gag.com", "token");
        entityManager.persist(expected);

        UrlReduction urlReduction = repository.findByToken("token");

        assertNotNull(urlReduction);
        assertEquals(expected.getId(), urlReduction.getId());
    }

}
