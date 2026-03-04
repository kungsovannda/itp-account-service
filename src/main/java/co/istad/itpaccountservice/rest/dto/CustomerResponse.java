package co.istad.itpaccountservice.rest.dto;

import co.istad.itpcommon.domain.valueobject.CustomerName;
import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        CustomerName name,
        String phoneNumber
) {
}
