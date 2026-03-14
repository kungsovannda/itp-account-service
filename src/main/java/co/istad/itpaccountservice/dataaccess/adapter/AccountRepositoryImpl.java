package co.istad.itpaccountservice.dataaccess.adapter;

import co.istad.itpaccountservice.application.ports.output.repository.AccountRepository;
import co.istad.itpaccountservice.dataaccess.entity.AccountEntity;
import co.istad.itpaccountservice.dataaccess.repository.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public AccountEntity save(AccountEntity accountEntity) {
        return accountJpaRepository.save(accountEntity);
    }
}
