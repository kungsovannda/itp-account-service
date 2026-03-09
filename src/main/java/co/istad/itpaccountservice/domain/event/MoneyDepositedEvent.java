package co.istad.itpaccountservice.domain.event;

import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.CustomerId;
import co.istad.itpcommon.domain.valueobject.Money;
import co.istad.itpcommon.domain.valueobject.TransactionId;

import java.time.ZonedDateTime;

public record MoneyDepositedEvent(
        AccountId accountId,
        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
        Money newBalance,
        String remark,
        ZonedDateTime createdAt
) {
}
