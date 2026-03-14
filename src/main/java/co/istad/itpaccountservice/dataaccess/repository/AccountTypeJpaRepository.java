package co.istad.itpaccountservice.dataaccess.repository;

import co.istad.itpaccountservice.dataaccess.entity.AccountTypeEntity;
import co.istad.itpcommon.domain.valueobject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountTypeJpaRepository extends JpaRepository<AccountTypeEntity, UUID> {
    Optional<AccountTypeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);
}
