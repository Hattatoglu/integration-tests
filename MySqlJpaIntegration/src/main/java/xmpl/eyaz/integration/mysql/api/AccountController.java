package xmpl.eyaz.integration.mysql.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmpl.eyaz.integration.mysql.command.CreateAccountCommand;
import xmpl.eyaz.integration.mysql.dto.CreateAccountRequest;
import xmpl.eyaz.integration.mysql.dto.CreateAccountResponse;
import xmpl.eyaz.integration.mysql.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request) {

        CreateAccountCommand command = new CreateAccountCommand();
        command.setUsername(request.getUsername());
        command.setName(request.getName());
        command.setSurname(request.getSurname());

        CreateAccountCommand result = accountService.createAccount(command);

        CreateAccountResponse response = new CreateAccountResponse();
        response.setAccountId(result.getAccountId().toString());
        response.setUsername(result.getUsername());
        response.setCreationDate(result.getCreationDate().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
