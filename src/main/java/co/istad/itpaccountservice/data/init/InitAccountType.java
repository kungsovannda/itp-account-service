package co.istad.itpaccountservice.data.init;

import co.istad.itpaccountservice.data.entity.AccountTypeEntity;
import co.istad.itpaccountservice.data.repository.AccountTypeRepository;
import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitAccountType {

    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void init(){
        if(accountTypeRepository.count() == 0){
            AccountTypeEntity checking = new AccountTypeEntity();
            checking.setAccountTypeId(UUID.randomUUID());
            checking.setAccountTypeCode(AccountTypeCode.CHECKING);

            AccountTypeEntity loan = new AccountTypeEntity();
            loan.setAccountTypeId(UUID.randomUUID());
            loan.setAccountTypeCode(AccountTypeCode.LOAN);

            AccountTypeEntity payroll = new AccountTypeEntity();
            payroll.setAccountTypeId(UUID.randomUUID());
            payroll.setAccountTypeCode(AccountTypeCode.PAYROLL);

            AccountTypeEntity saving = new AccountTypeEntity();
            saving.setAccountTypeId(UUID.randomUUID());
            saving.setAccountTypeCode(AccountTypeCode.SAVING);

            accountTypeRepository.saveAll(
                    List.of(
                            checking,
                            loan,
                            payroll,
                            saving
                    )
            );
        }
    }
}
