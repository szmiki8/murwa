package com.example.murwa.web;

import com.example.murwa.MurwaApplication;
import com.example.murwa.domain.UrlReduction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MurwaApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlReductionControllerTest {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Before
    public void setup() throws Exception {
        template.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders().add("Content-Type", "application/json");
                    request.getHeaders().add("Accept", "application/json");

                    return execution.execute(request, body);
                }));

        // FIXME: cleanup between test cases or exact order....?
        template.delete(getUrl(""));
    }

    @Test
    public void testUrlReductionNotFound() {
        ResponseEntity<String> response = template.getForEntity(getUrl("/123"), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testInitialRepositoryIsEmpty() {
        ResponseEntity<String> response = template.getForEntity(getUrl(""), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody(), isJson(withJsonPath("$", hasSize(0))));
    }

    @Test
    public void testAddUrlReductionWithoutExplicitToken() {
        final String url = "http://spring.io/";

        UrlReductionCreateRequest request = new UrlReductionCreateRequest(url);
        HttpEntity<UrlReductionCreateRequest> entity = new HttpEntity<>(request);

        ResponseEntity<String> response = template.postForEntity(getUrl(""), entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody(), isJson(
                withJsonPath("$.url", equalTo(url))
        ));
    }

    @Test
    public void testAddUrlReductionWithExplicitTokenNotExisting() {
        final String url = "http://spring.io/";
        final String token = "token";

        UrlReductionCreateRequest request = new UrlReductionCreateRequest(url, token);
        HttpEntity<UrlReductionCreateRequest> entity = new HttpEntity<>(request);

        ResponseEntity<String> response = template.postForEntity(getUrl(""), entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody(), isJson(
                withJsonPath("$.token", equalTo(token))
        ));
        assertThat(response.getBody(), isJson(
                withJsonPath("$.url", equalTo(url))
        ));
    }

    @Test
    public void testAddUrlReductionEmptyUrl() {
        HttpEntity<UrlReduction> entity = new HttpEntity<>(new UrlReduction(""));

        ResponseEntity<String> response = template.postForEntity(getUrl(""), entity, String.class);

        assertThat(response.getStatusCode()).isGreaterThanOrEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testAddMultipleUrlReductionsWithSameTokenGeneratesConstraintViolation() {
        final String token = "token";

        HttpEntity<UrlReductionCreateRequest> entity1 = new HttpEntity<>(new UrlReductionCreateRequest("http://9gag.com", token));
        HttpEntity<UrlReductionCreateRequest> entity2 = new HttpEntity<>(new UrlReductionCreateRequest("http://spring.io/", token));

        ResponseEntity<String> response1 = template.postForEntity(getUrl(""), entity1, String.class);
        ResponseEntity<String> response2 = template.postForEntity(getUrl(""), entity2, String.class);

        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getStatusCode()).isGreaterThanOrEqualTo(HttpStatus.BAD_REQUEST);
    }

    private String getUrl(String path) {
        return "http://localhost:" + this.port + "/api/url_reduction" + path;
    }

}
