package co.istad.itpaccountservice.domain.handler;

import co.istad.itpaccountservice.data.entity.AccountEntity;
import co.istad.itpaccountservice.data.entity.AccountTypeEntity;
import co.istad.itpaccountservice.data.repository.AccountRepository;
import co.istad.itpaccountservice.data.repository.AccountTypeRepository;
import co.istad.itpaccountservice.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountListener {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){

        AccountTypeEntity accountTypeEntity = accountTypeRepository.findByAccountTypeCode(event.accountTypeCode()).orElse(null);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(accountTypeEntity);
        accountEntity.setAccountHolder(event.accountHolder());
        accountEntity.setAccountId(event.accountId().accountId());
        accountEntity.setAccountNumber(event.accountNumber());
        accountEntity.setBalance(event.initialBalance());
        accountEntity.setStatus(event.status());
        accountEntity.setBranchId(event.branchId().branchId());
        accountEntity.setCreatedBy(event.createdBy());
        accountEntity.setCreatedAt(event.createdAt());
        accountEntity.setCustomerId(event.customerId().customerId());

        accountRepository.save(accountEntity);

    }
}
