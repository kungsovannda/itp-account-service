package co.istad.itpaccountservice.domain.command;

import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.*;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateAccountCommand(

        @TargetAggregateIdentifier
        AccountId accountId,

        String accountNumber,

         String accountHolder,

         CustomerId customerId,

         AccountTypeCode accountTypeCode,

         BranchId branchId,

         Money initialBalance
) {
}
