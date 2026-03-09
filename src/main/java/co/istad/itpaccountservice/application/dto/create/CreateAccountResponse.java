package co.istad.itpaccountservice.application.dto.create;

import java.util.UUID;

public record CreateAccountResponse(
        UUID accountId,
        String accountNumber,
        String message
) {
}
