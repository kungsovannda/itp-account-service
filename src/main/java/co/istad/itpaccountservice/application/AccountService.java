package co.istad.itpaccountservice.application;

import co.istad.itpaccountservice.application.dto.create.CreateAccountRequest;
import co.istad.itpaccountservice.application.dto.create.CreateAccountResponse;
import co.istad.itpaccountservice.application.dto.deposit.DepositRequest;
import co.istad.itpaccountservice.application.dto.deposit.DepositResponse;
import co.istad.itpaccountservice.application.dto.freeze.FreezeRequest;
import co.istad.itpaccountservice.application.dto.freeze.FreezeResponse;
import co.istad.itpaccountservice.application.dto.withdraw.WithdrawRequest;
import co.istad.itpaccountservice.application.dto.withdraw.WithdrawResponse;
import co.istad.itpcommon.domain.valueobject.AccountId;

public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    WithdrawResponse withdraw(AccountId accountId, WithdrawRequest withdrawRequest);
    DepositResponse deposit(AccountId accountId,DepositRequest depositRequest);
    FreezeResponse freeze(AccountId accountId, FreezeRequest freezeRequest);

}
