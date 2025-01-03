package xmpl.eyaz.integration.mysql.service;

import xmpl.eyaz.integration.mysql.command.CreateAccountCommand;
import xmpl.eyaz.integration.mysql.command.DeleteAccountCommand;
import xmpl.eyaz.integration.mysql.command.ReadAccountCommand;
import xmpl.eyaz.integration.mysql.command.UpdateAccountCommand;

public interface AccountService {

    CreateAccountCommand createAccount(CreateAccountCommand command);
    ReadAccountCommand readAccount(ReadAccountCommand command);
    UpdateAccountCommand updateAccount(UpdateAccountCommand command);
    DeleteAccountCommand deleteAccount(DeleteAccountCommand command);
}
