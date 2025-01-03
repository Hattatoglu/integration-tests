package xmpl.eyaz.integration.mysql.command;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ReadAccountCommand {

    private UUID accountId;
    private String name;
    private ZonedDateTime creationDate;

    public ReadAccountCommand() {
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
