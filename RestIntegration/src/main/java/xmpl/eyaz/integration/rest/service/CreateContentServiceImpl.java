package xmpl.eyaz.integration.rest.service;

import org.springframework.stereotype.Service;
import xmpl.eyaz.integration.rest.command.CreateContentCommand;
import xmpl.eyaz.integration.rest.command.FindContentCommand;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class CreateContentServiceImpl implements CreateContentService{
    @Override
    public CreateContentCommand createContent(CreateContentCommand command) {

        command.setUrl(command.getUsername()+command.getContent());
        command.setContentId(UUID.randomUUID());
        command.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));

        return command;
    }

    @Override
    public FindContentCommand findContent(FindContentCommand command) {

        command.setContentId(UUID.randomUUID());
        command.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));
        command.setContent("content query for : " + command.getUrl());

        return command;
    }
}
