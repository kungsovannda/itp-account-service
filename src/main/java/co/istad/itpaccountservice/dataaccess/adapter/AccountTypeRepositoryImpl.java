package co.istad.itpaccountservice.dataaccess.adapter;

import co.istad.itpaccountservice.application.ports.output.repository.AccountTypeRepository;
import co.istad.itpaccountservice.dataaccess.entity.AccountTypeEntity;
import co.istad.itpaccountservice.dataaccess.repository.AccountTypeJpaRepository;
import co.istad.itpcommon.domain.valueobject.AccountTypeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountTypeRepositoryImpl implements AccountTypeRepository {

    private final AccountTypeJpaRepository accountTypeJpaRepository;

    @Override
    public Optional<AccountTypeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode) {
        return accountTypeJpaRepository.findByAccountTypeCode(accountTypeCode);
    }
}
