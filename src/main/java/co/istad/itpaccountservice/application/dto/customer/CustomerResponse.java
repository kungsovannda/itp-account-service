package co.istad.itpaccountservice.application.dto.customer;

import co.istad.itpcommon.domain.valueobject.CustomerName;
import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        CustomerName name,
        String phoneNumber
) {
}
