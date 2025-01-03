package xmpl.eyaz.integration.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmpl.eyaz.integration.mysql.model.AccountEntity;

import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
}
