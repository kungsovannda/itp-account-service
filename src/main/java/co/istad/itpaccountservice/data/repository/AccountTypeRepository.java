package co.istad.itpaccountservice.data.repository;

import co.istad.itpaccountservice.data.entity.AccountTypeEntity;
import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, UUID> {
    Optional<AccountTypeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);
}
