package co.istad.itpaccountservice.rest;

import co.istad.itpaccountservice.application.AccountService;
import co.istad.itpaccountservice.application.dto.create.CreateAccountResponse;
import co.istad.itpaccountservice.application.dto.deposit.DepositRequest;
import co.istad.itpaccountservice.application.dto.deposit.DepositResponse;
import co.istad.itpaccountservice.application.dto.freeze.FreezeRequest;
import co.istad.itpaccountservice.application.dto.freeze.FreezeResponse;
import co.istad.itpaccountservice.application.dto.withdraw.WithdrawRequest;
import co.istad.itpaccountservice.application.dto.withdraw.WithdrawResponse;
import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.application.dto.create.CreateAccountRequest;
import co.istad.itpcommon.domain.valueobject.AccountId;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request){
        return accountService.createAccount(request);
    }

    @PostMapping("/{accountId}/deposit")
    public DepositResponse deposit(
            @PathVariable UUID accountId,
            @RequestBody DepositRequest depositRequest
    ){
        return accountService.deposit(
                new AccountId(accountId),
                depositRequest
        );
    }

    @PostMapping("/{accountId}/withdraw")
    public WithdrawResponse withdraw(
            @PathVariable UUID accountId,
            @RequestBody WithdrawRequest withdrawRequest
    ){
        return accountService.withdraw(
                new AccountId(accountId),
                withdrawRequest
        );
    }

    @PostMapping("/{accountId}/freeze")
    public FreezeResponse freeze(
            @PathVariable UUID accountId,
            @RequestBody FreezeRequest freezeRequest
    ){
        return accountService.freeze(
                new AccountId(accountId),
                freezeRequest
        );
    }

}
