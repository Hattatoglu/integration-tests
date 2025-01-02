package xmpl.eyaz.integration.rest.service;

import xmpl.eyaz.integration.rest.command.CreateContentCommand;
import xmpl.eyaz.integration.rest.command.FindContentCommand;

public interface CreateContentService {

    CreateContentCommand createContent(CreateContentCommand command);
    FindContentCommand findContent(FindContentCommand command);
}
