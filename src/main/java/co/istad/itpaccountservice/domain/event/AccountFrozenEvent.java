package co.istad.itpaccountservice.domain.event;

import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.AccountStatus;
import co.istad.itpcommon.domain.valueobject.CustomerId;

import java.time.ZonedDateTime;

public record AccountFrozenEvent (
        AccountId accountId,
        CustomerId customerId,
        AccountStatus previousStatus,
        AccountStatus newStatus,
        String reason,
        String requestedBy,
        ZonedDateTime createdAt
){
}
