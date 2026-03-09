package co.istad.itpaccountservice.application;

import co.istad.itpaccountservice.application.dto.create.CreateAccountRequest;
import co.istad.itpaccountservice.application.dto.create.CreateAccountResponse;
import co.istad.itpaccountservice.application.dto.deposit.DepositRequest;
import co.istad.itpaccountservice.application.dto.deposit.DepositResponse;
import co.istad.itpaccountservice.application.dto.freeze.FreezeRequest;
import co.istad.itpaccountservice.application.dto.freeze.FreezeResponse;
import co.istad.itpaccountservice.application.dto.withdraw.WithdrawRequest;
import co.istad.itpaccountservice.application.dto.withdraw.WithdrawResponse;
import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.domain.command.DepositMoneyCommand;
import co.istad.itpaccountservice.domain.command.FreezeAccountCommand;
import co.istad.itpaccountservice.domain.command.WithdrawMoneyCommand;
import co.istad.itpcommon.domain.valueobject.AccountId;
import co.istad.itpcommon.domain.valueobject.TransactionId;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CommandGateway commandGateway;


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        AccountId accountId = new AccountId(UUID.randomUUID());
        commandGateway.sendAndWait(
                new CreateAccountCommand(
                        accountId,
                        createAccountRequest.accountNumber(),
                        createAccountRequest.accountHolder(),
                        createAccountRequest.customerId(),
                        createAccountRequest.accountTypeCode(),
                        createAccountRequest.branchId(),
                        createAccountRequest.initialBalance()
                )
        );
        return new CreateAccountResponse(
                accountId.accountId(),
                createAccountRequest.accountNumber(),
                "Account has been created successfully"
        );
    }

    @Override
    public WithdrawResponse withdraw(AccountId accountId, WithdrawRequest withdrawRequest) {
        TransactionId transactionId = new TransactionId(UUID.randomUUID());
        commandGateway.sendAndWait(
                new WithdrawMoneyCommand(
                        accountId,
                        withdrawRequest.customerId(),
                        transactionId,
                        withdrawRequest.amount(),
                        withdrawRequest.remark()
                )
        );
        return new WithdrawResponse(
                accountId,
                transactionId,
                "Money has been withdrawn successfully"
        );
    }

    @Override
    public DepositResponse deposit(AccountId accountId, DepositRequest depositRequest) {
        TransactionId transactionId = new TransactionId(UUID.randomUUID());
        commandGateway.sendAndWait(
                new DepositMoneyCommand(
                        accountId,
                        depositRequest.customerId(),
                        transactionId,
                        depositRequest.amount(),
                        depositRequest.remark()
                )
        );
        return new DepositResponse(
                accountId,
                transactionId,
                "Money has been deposited successfully"
        );
    }

    @Override
    public FreezeResponse freeze(AccountId accountId, FreezeRequest freezeRequest) {
        commandGateway.sendAndWait(
                new FreezeAccountCommand(
                        accountId,
                        freezeRequest.remark(),
                        freezeRequest.requestedBy()
                )
        );
        return new FreezeResponse(
                accountId,
                "Account has been frozen successfully"
        );
    }
}
