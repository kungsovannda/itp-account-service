package co.istad.itpaccountservice.domain.event;

import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.*;

import java.time.ZonedDateTime;

public record AccountCreatedEvent(
        AccountId accountId,

        String accountNumber,

        String accountHolder,

        CustomerId customerId,

        AccountTypeCode accountTypeCode,

        BranchId branchId,

        Money initialBalance,

        AccountStatus status,

        ZonedDateTime createdAt,

        String createdBy
) {
}
