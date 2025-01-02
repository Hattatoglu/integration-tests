package xmpl.eyaz.integration.rest.api;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xmpl.eyaz.integration.rest.config.AbstractIT;
import xmpl.eyaz.integration.rest.dto.ContentQueryResponse;
import xmpl.eyaz.integration.rest.test.IT;

import static org.assertj.core.api.Assertions.*;

@IT
class ContentQueryControllerTest extends AbstractIT {

    private final ParameterizedTypeReference<ContentQueryResponse> retrieveTicketResponseType = new ParameterizedTypeReference<>() {};
    @Test
    void should_retrieve_content() {

        String testUrl = "testcontenturl";

        ResponseEntity<ContentQueryResponse> responseEntity = testRestTemplate.exchange("/api/v1/rests?url=" + testUrl,
                HttpMethod.GET,
                new HttpEntity<>(null, null), retrieveTicketResponseType);

        // then - assert response
        assertThat(responseEntity).isNotNull()
                .returns(HttpStatus.OK, from(ResponseEntity::getStatusCode));

        assertThat(responseEntity.getBody()).isNotNull();
    }

}