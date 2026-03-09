package co.istad.itpaccountservice.application.dto.withdraw;

import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.TransactionId;

public record WithdrawResponse(
        AccountId accountId,
        TransactionId transactionId,
        String message
) {
}
