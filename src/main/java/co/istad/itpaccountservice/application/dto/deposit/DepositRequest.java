package co.istad.itpaccountservice.application.dto.deposit;

import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.CustomerId;
import co.istad.itpcommon.domain.valueobject.Money;

public record DepositRequest(
        CustomerId customerId,
        Money amount,
        String remark
) {
}
