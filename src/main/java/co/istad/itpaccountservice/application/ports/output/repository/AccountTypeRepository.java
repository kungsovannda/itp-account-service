package co.istad.itpaccountservice.application.ports.output.repository;

import co.istad.itpaccountservice.dataaccess.entity.AccountTypeEntity;
import co.istad.itpcommon.domain.valueobject.AccountTypeCode;

import java.util.Optional;

public interface AccountTypeRepository {

    Optional<AccountTypeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);

}
