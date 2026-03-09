package co.istad.itpaccountservice.application.dto.freeze;

import co.istad.itpcommon.domain.valueobject.CustomerId;

public record FreezeRequest(
        String remark,
        String requestedBy
) {
}
