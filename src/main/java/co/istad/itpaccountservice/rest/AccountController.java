package co.istad.itpaccountservice.rest;

import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.rest.dto.CreateAccountRequest;
import co.istad.itpcommon.domain.valueobject.AccountId;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CommandGateway commandGateway;

    @PostMapping
    public AccountId createAccount(@RequestBody CreateAccountRequest request){
        AccountId accountId = new AccountId(UUID.randomUUID());
         commandGateway.sendAndWait(
                new CreateAccountCommand(
                        accountId,
                        request.accountNumber(),
                        request.accountHolder(),
                        request.customerId(),
                        request.accountTypeCode(),
                        request.branchId(),
                        request.initialBalance()
                )
        );
         return accountId;
    }
}
