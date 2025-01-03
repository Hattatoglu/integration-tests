package xmpl.eyaz.integration.mysql.service;

import org.springframework.stereotype.Service;
import xmpl.eyaz.integration.mysql.command.CreateAccountCommand;
import xmpl.eyaz.integration.mysql.command.DeleteAccountCommand;
import xmpl.eyaz.integration.mysql.command.ReadAccountCommand;
import xmpl.eyaz.integration.mysql.command.UpdateAccountCommand;
import xmpl.eyaz.integration.mysql.model.AccountEntity;
import xmpl.eyaz.integration.mysql.repository.AccountJpaRepository;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountJpaRepository accountJpaRepository;

    public AccountServiceImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public CreateAccountCommand createAccount(CreateAccountCommand command) {

        AccountEntity entity = new AccountEntity();
        entity.setUsername(command.getUsername());
        entity.setName(command.getName());
        entity.setSurname(command.getSurname());
        entity.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));

        AccountEntity result = accountJpaRepository.save(entity);

        command.setAccountId(result.getAccountId());
        command.setCreationDate(result.getCreationDate());

        return command;
    }

    @Override
    public ReadAccountCommand readAccount(ReadAccountCommand command) {
        Optional<AccountEntity> optionalAccount = accountJpaRepository.findById(command.getAccountId());

        if (optionalAccount.isPresent()) {
            AccountEntity entity = optionalAccount.get();
            command.setName(entity.getName());
            command.setCreationDate(entity.getCreationDate());
            return command;

        } else {
            throw new RuntimeException("account.not.found");
        }
    }

    @Override
    public UpdateAccountCommand updateAccount(UpdateAccountCommand command) {
        Optional<AccountEntity> optionalAccount = accountJpaRepository.findById(command.getAccountId());

        if (optionalAccount.isPresent()) {
            AccountEntity entity = optionalAccount.get();
            entity.setName(command.getUpdateName());

            AccountEntity answer = accountJpaRepository.save(entity);
            command.setName(entity.getName());

            return command;
        } else {
            throw new RuntimeException("account.not.found");
        }
    }

    @Override
    public DeleteAccountCommand deleteAccount(DeleteAccountCommand command) {
        accountJpaRepository.deleteById(command.getAccountId());
        return command;
    }
}
