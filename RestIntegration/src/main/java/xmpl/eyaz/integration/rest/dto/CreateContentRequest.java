package xmpl.eyaz.integration.rest.dto;

import jakarta.validation.constraints.NotNull;

public class CreateContentRequest {

    @NotNull private String username;
    @NotNull private String content;

    public CreateContentRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
