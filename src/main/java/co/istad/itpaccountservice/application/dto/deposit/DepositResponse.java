package co.istad.itpaccountservice.application.dto.deposit;

import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.TransactionId;

public record DepositResponse (
        AccountId accountId,
        TransactionId transactionId,
        String message
){
}
