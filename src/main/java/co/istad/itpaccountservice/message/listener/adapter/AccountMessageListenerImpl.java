package co.istad.itpaccountservice.message.listener.adapter;

import co.istad.itpaccountservice.application.ports.output.message.listener.AccountMessageListener;
import co.istad.itpaccountservice.application.ports.output.repository.AccountRepository;
import co.istad.itpaccountservice.application.ports.output.repository.AccountTypeRepository;
import co.istad.itpaccountservice.dataaccess.entity.AccountEntity;
import co.istad.itpaccountservice.dataaccess.entity.AccountTypeEntity;
import co.istad.itpcommon.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountMessageListenerImpl implements AccountMessageListener {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public void on(AccountCreatedEvent event) {
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
