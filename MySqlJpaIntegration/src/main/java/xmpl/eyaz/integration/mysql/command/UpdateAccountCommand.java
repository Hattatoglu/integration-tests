package xmpl.eyaz.integration.mysql.command;

import java.util.UUID;

public class UpdateAccountCommand {

    private UUID accountId;
    private String updateName;
    private String name;

    public UpdateAccountCommand() {
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
