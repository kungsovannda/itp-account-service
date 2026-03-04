package co.istad.itpaccountservice.domain.command;

import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.*;
import org.axonframework.modelling.command.AggregateIdentifier;

public record CreateAccountCommand(

        @AggregateIdentifier
        AccountId accountId,

        String accountNumber,

         String accountHolder,

         CustomerId customerId,

         AccountTypeCode accountTypeCode,

         BranchId branchId,

         Money initialBalance
) {
}
