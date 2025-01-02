package xmpl.eyaz.integration.rest.command;

import java.time.ZonedDateTime;
import java.util.UUID;

public class FindContentCommand {

    private String url;

    private UUID contentId;
    private ZonedDateTime creationDate;
    private String content;

    public FindContentCommand() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getContentId() {
        return contentId;
    }

    public void setContentId(UUID contentId) {
        this.contentId = contentId;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
