package co.istad.itpaccountservice.application.dto.freeze;

import co.istad.itpcommon.domain.valueobject.AccountId;

public record FreezeResponse(
        AccountId accountId,
        String message
) {
}
