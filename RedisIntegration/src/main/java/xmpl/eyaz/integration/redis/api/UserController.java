package xmpl.eyaz.integration.redis.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;
import xmpl.eyaz.integration.redis.dto.CreateUserRequest;
import xmpl.eyaz.integration.redis.dto.CreateUserResponse;
import xmpl.eyaz.integration.redis.dto.FindUserResponse;
import xmpl.eyaz.integration.redis.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {

        CreateUserCommand command = new CreateUserCommand(request.getUsername(), request.getName());

        CreateUserCommand answer = userService.createUser(command);

        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(answer.getUser().getUserId().toString());
        response.setCreationTime(answer.getUser().getCreationTime().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<FindUserResponse> findUser(@RequestParam String userId) {
        FindUserCommand command = new FindUserCommand();
        command.setUserId(UUID.fromString(userId));

        FindUserCommand answer = userService.findUser(command);

        FindUserResponse response = new FindUserResponse();
        response.setUserStatus(answer.getUser().isActive());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
