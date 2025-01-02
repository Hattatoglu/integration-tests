package xmpl.eyaz.integration.rest.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmpl.eyaz.integration.rest.command.CreateContentCommand;
import xmpl.eyaz.integration.rest.dto.CreateContentRequest;
import xmpl.eyaz.integration.rest.dto.CreateContentResponse;
import xmpl.eyaz.integration.rest.service.CreateContentService;

@RestController
@RequestMapping("/api/v1/rests")
public class CreateContentController {

    private final CreateContentService createContentService;

    public CreateContentController(CreateContentService createContentService) {
        this.createContentService = createContentService;
    }

    @PostMapping
    public ResponseEntity<CreateContentResponse> createContent(@Valid @RequestBody CreateContentRequest request) {
        CreateContentCommand command = new CreateContentCommand();
        command.setUsername(request.getUsername());
        command.setContent(request.getContent());

        CreateContentCommand result = createContentService.createContent(command);

        CreateContentResponse response = new CreateContentResponse();
        response.setUrl(result.getUrl());
        response.setContentId(result.getContentId().toString());
        response.setCreationDate(result.getCreationDate().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
