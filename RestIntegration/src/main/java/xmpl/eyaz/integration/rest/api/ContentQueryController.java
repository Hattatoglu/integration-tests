package xmpl.eyaz.integration.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xmpl.eyaz.integration.rest.command.FindContentCommand;
import xmpl.eyaz.integration.rest.dto.ContentQueryResponse;
import xmpl.eyaz.integration.rest.service.CreateContentService;

@RestController
@RequestMapping("/api/v1/rests")
public class ContentQueryController {

    private final CreateContentService createContentService;

    public ContentQueryController(CreateContentService createContentService) {
        this.createContentService = createContentService;
    }

    @GetMapping
    public ResponseEntity<ContentQueryResponse> getQuery(@RequestParam String url) {

        FindContentCommand command = new FindContentCommand();
        command.setUrl(url);

        FindContentCommand result = createContentService.findContent(command);

        ContentQueryResponse response = new ContentQueryResponse();
        response.setContentId(result.getContentId().toString());
        response.setCreationDate(result.getCreationDate().toString());
        response.setContent(result.getContent());

        return ResponseEntity.ok(response);
    }
}
