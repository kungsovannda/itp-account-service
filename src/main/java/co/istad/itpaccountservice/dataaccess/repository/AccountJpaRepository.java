package co.istad.itpaccountservice.dataaccess.repository;

import co.istad.itpaccountservice.dataaccess.entity.AccountEntity;
import co.istad.itpcommon.domain.valueobject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);
}
