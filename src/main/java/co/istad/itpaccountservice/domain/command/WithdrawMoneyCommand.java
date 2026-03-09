package co.istad.itpaccountservice.domain.command;

import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.CustomerId;
import co.istad.itpcommon.domain.valueobject.Money;
import co.istad.itpcommon.domain.valueobject.TransactionId;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record WithdrawMoneyCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
        String remark
) {
}
