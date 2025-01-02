package xmpl.eyaz.integration.rest.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xmpl.eyaz.integration.rest.config.AbstractIT;
import xmpl.eyaz.integration.rest.dto.CreateContentRequest;
import xmpl.eyaz.integration.rest.dto.CreateContentResponse;
import xmpl.eyaz.integration.rest.test.IT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@IT
class CreateContentControllerTest extends AbstractIT {

    private final ParameterizedTypeReference<CreateContentResponse> responseType = new ParameterizedTypeReference<>() {};

    @Test
    void should_createContent() {
        //given
        CreateContentRequest request = new CreateContentRequest();
        request.setUsername("test");
        request.setContent("content");

        //when
        ResponseEntity<CreateContentResponse> responseEntity = testRestTemplate.exchange("/api/v1/rests", HttpMethod.POST, new HttpEntity<>(request), responseType);

        //then - assert response
        assertThat(responseEntity).isNotNull()
                .returns(HttpStatus.CREATED, from(ResponseEntity::getStatusCode));

        //then - assert data
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getContentId()).isNotNull();
        assertThat(responseEntity.getBody().getCreationDate()).isNotNull();
        assertThat(responseEntity.getBody())
                .returns("testcontent", from(CreateContentResponse::getUrl));
    }

    @Test
    void shouldThrowException_whenRequestNotValid() {
        //given
        CreateContentRequest request = new CreateContentRequest();
        request.setUsername("test");
        request.setContent(null);

        //when
        ResponseEntity<CreateContentResponse> responseEntity = testRestTemplate.exchange("/api/v1/rests", HttpMethod.POST, new HttpEntity<>(request), responseType);


        //then - assert response
        assertThat(responseEntity).isNotNull()
                .returns(HttpStatus.BAD_REQUEST, from(ResponseEntity::getStatusCode));

    }

}