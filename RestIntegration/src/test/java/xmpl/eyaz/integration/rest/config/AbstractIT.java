package xmpl.eyaz.integration.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

public class AbstractIT {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @LocalServerPort
    protected Integer port;
}
