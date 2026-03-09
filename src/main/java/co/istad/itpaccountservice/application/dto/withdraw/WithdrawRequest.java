package co.istad.itpaccountservice.application.dto.withdraw;

import co.istad.itpcommon.domain.valueobject.CustomerId;
import co.istad.itpcommon.domain.valueobject.Money;

public record WithdrawRequest(
        CustomerId customerId,
        Money amount,
        String remark
) {
}
