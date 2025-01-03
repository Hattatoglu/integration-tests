package xmpl.eyaz.integration.mysql.command;

import java.util.UUID;

public class DeleteAccountCommand {

    private UUID accountId;

    public DeleteAccountCommand() {
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}
