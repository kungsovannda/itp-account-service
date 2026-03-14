package co.istad.itpaccountservice.application.ports.output.repository;

import co.istad.itpaccountservice.dataaccess.entity.AccountEntity;

public interface AccountRepository {

    AccountEntity save(AccountEntity accountEntity);
}
