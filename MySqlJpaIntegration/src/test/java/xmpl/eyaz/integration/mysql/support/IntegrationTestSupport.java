package xmpl.eyaz.integration.mysql.support;

import org.springframework.beans.factory.annotation.Autowired;
import xmpl.eyaz.integration.mysql.model.AccountEntity;
import xmpl.eyaz.integration.mysql.repository.AccountJpaRepository;
import xmpl.eyaz.integration.mysql.service.AccountService;
import xmpl.eyaz.integration.mysql.service.AccountServiceImpl;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IntegrationTestSupport {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    public List<UUID> accountIdList;

    protected AccountService getAccountService() {
        return new AccountServiceImpl(accountJpaRepository);
    }

    protected void initiateDatabase(int accountSize) {

        accountIdList = new ArrayList<>();

        for (int i = 0; i < accountSize; i++) {
            AccountEntity entity = new AccountEntity();
            entity.setUsername("username_"+i);
            entity.setName("name_"+i);
            entity.setSurname("surname_"+i);
            entity.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));

            AccountEntity answer = accountJpaRepository.save(entity);

            accountIdList.add(answer.getAccountId());
        }

    }

    protected UUID getAccountId(int index) {
        return accountIdList.get(index);
    }
}
