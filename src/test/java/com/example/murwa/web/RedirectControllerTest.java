package com.example.murwa.web;

import com.example.murwa.MurwaApplication;
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
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MurwaApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RedirectControllerTest {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Test
    public void testSuccessfulUrlRedirect() {
      testUrlRedirect("s", HttpStatus.OK, true);
    }

    @Test
    public void testUnsuccessfulUrlRedirect() {
        testUrlRedirect("s2", HttpStatus.NOT_FOUND, false);
    }

    private void testUrlRedirect(String token, HttpStatus status, Boolean createUrlReduction) {
        final String url = "http://spring.io/";

        if (createUrlReduction) {
            UrlReductionCreateRequest request = new UrlReductionCreateRequest(url, token);
            HttpEntity<UrlReductionCreateRequest> entity = new HttpEntity<>(request);

            ResponseEntity<String> response = template.postForEntity(getUrl("api/url_reduction"), entity, String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        ResponseEntity<?> redirectResponse = template.getForEntity(getUrl("r/" + token), String.class);
        assertThat(redirectResponse.getStatusCode()).isEqualTo(status);
    }

    private String getUrl(String path) {
        return "http://localhost:" + this.port + "/" + path;
    }

}
