package co.istad.itpaccountservice.application.dto.create;

import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.BranchId;
import co.istad.itpcommon.domain.valueobject.CustomerId;
import co.istad.itpcommon.domain.valueobject.Money;

public record CreateAccountRequest(
        String accountNumber,

        String accountHolder,

        CustomerId customerId,

        AccountTypeCode accountTypeCode,

        BranchId branchId,

        Money initialBalance
) {
}
