package xmpl.eyaz.integration.mysql.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xmpl.eyaz.integration.mysql.command.CreateAccountCommand;
import xmpl.eyaz.integration.mysql.command.DeleteAccountCommand;
import xmpl.eyaz.integration.mysql.command.ReadAccountCommand;
import xmpl.eyaz.integration.mysql.command.UpdateAccountCommand;
import xmpl.eyaz.integration.mysql.config.IT;
import xmpl.eyaz.integration.mysql.model.AccountEntity;
import xmpl.eyaz.integration.mysql.repository.AccountJpaRepository;
import xmpl.eyaz.integration.mysql.support.IntegrationTestSupport;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IT
class AccountServiceImplTest extends IntegrationTestSupport {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = getAccountService();
    }

    @Test
    void should_CreateAccount() {
        //Create operation

        //given
        CreateAccountCommand command = new CreateAccountCommand();
        command.setUsername("integration");
        command.setName("test");
        command.setSurname("lastname");

        //when
        CreateAccountCommand answer = accountService.createAccount(command);

        //then
        assertThat(answer).isNotNull();
        assertThat(answer.getAccountId()).isNotNull();
        assertThat(answer.getCreationDate()).isNotNull();
        assertThat(answer.getUsername()).isNotNull().isEqualTo(command.getUsername());
        assertThat(answer.getName()).isNotNull().isEqualTo(command.getName());
        assertThat(answer.getSurname()).isNotNull().isEqualTo(command.getSurname());
    }

    @Test
    void should_findByID() {
        //Read operation

        initiateDatabase(3);
        UUID accountId = getAccountId(1);

        //given
        ReadAccountCommand command = new ReadAccountCommand();
        command.setAccountId(accountId);

        //when
        ReadAccountCommand answer = accountService.readAccount(command);

        //then
        assertThat(answer).isNotNull();
        assertThat(answer.getAccountId()).isNotNull().isEqualTo(accountId);
        assertThat(answer.getName()).isNotNull();
        assertThat(answer.getCreationDate()).isNotNull();
    }

    @Test
    void shouldThrowException_whenReadAccountForUnknownAccountId() {
        //Read operation

        initiateDatabase(3);
        UUID accountId = UUID.randomUUID();

        //given
        ReadAccountCommand command = new ReadAccountCommand();
        command.setAccountId(accountId);

        //when
        Throwable answer = assertThrows(Throwable.class, () -> accountService.readAccount(command));

        //then
        assertThat(answer).isNotNull();
        assertThat(answer.getMessage()).isEqualTo("account.not.found");
    }

    @Test
    void should_updateAccount() {
        //Update operation

        initiateDatabase(5);
        UUID accountId = getAccountId(4);

        //given
        UpdateAccountCommand command = new UpdateAccountCommand();
        command.setAccountId(accountId);
        command.setUpdateName("update");
        command.setName(null);

        UpdateAccountCommand answer = accountService.updateAccount(command);

        assertThat(answer).isNotNull();
        assertThat(answer.getName()).isNotNull().isEqualTo(command.getUpdateName());
    }

    @Test
    void shouldThrowException_whenUpdateAccountForUnknownAccountId() {
        //update operation
        initiateDatabase(3);
        UUID accountId = UUID.randomUUID();

        //given
        UpdateAccountCommand command = new UpdateAccountCommand();
        command.setAccountId(accountId);
        command.setUpdateName("update");

        //when
        Throwable answer = assertThrows(Throwable.class, () -> accountService.updateAccount(command));

        //then
        assertThat(answer).isNotNull();
        assertThat(answer.getMessage()).isEqualTo("account.not.found");
    }

    @Test
    void should_delete_all_tickets() {
        //Delete Operation

        initiateDatabase(3);
        UUID accountId = getAccountId(2);

        //given
        DeleteAccountCommand command = new DeleteAccountCommand();
        command.setAccountId(accountId);

        // when
        DeleteAccountCommand answer = accountService.deleteAccount(command);

        // then
        Optional<AccountEntity> account = accountJpaRepository.findById(accountId);

        assertThat(account).isEmpty();
    }

}