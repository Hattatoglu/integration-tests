package xmpl.eyaz.integration.message.restaurant.config;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Tag("integrationTest")
@ExtendWith(SpringExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
public @interface IT {
}
