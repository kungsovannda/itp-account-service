package co.istad.itpaccountservice.domain.aggregate;

import co.istad.itpaccountservice.data.entity.CustomerEntity;
import co.istad.itpaccountservice.data.repository.CustomerRepository;
import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.domain.command.DepositMoneyCommand;
import co.istad.itpaccountservice.domain.command.FreezeAccountCommand;
import co.istad.itpaccountservice.domain.command.WithdrawMoneyCommand;
import co.istad.itpaccountservice.domain.event.AccountCreatedEvent;
import co.istad.itpaccountservice.domain.event.AccountFrozenEvent;
import co.istad.itpaccountservice.domain.event.MoneyDepositedEvent;
import co.istad.itpaccountservice.domain.event.MoneyWithdrawnEvent;
import co.istad.itpaccountservice.domain.exception.AccountDomainException;
import co.istad.itpaccountservice.domain.validate.AccountValidate;
import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private AccountId accountId;

    private String accountNumber;

    private String accountHolder;

    private CustomerId customerId;

    private AccountTypeCode accountTypeCode;

    private BranchId branchId;

    private Money balance;

    private AccountStatus status;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd, @Autowired CustomerRepository customerRepository){
        log.info("Aggregate received CreateAccountCommand: {}",cmd);

        AccountValidate.validateAccountNumber(cmd.accountNumber());
        validateAccountType(cmd.accountTypeCode());
        validateInitialBalance(cmd.initialBalance());

        CustomerEntity customer = customerRepository.findById(cmd.customerId().customerId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")
        );

        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        cmd.accountId(),
                        cmd.accountNumber(),
                        resolveCustomerName(customer.getCustomerName()),
                        cmd.customerId(),
                        cmd.accountTypeCode(),
                        cmd.branchId(),
                        cmd.initialBalance(),
                        AccountStatus.ACTIVE,
                        ZonedDateTime.now(),
                        cmd.customerId().customerId().toString()
                )
        );
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent event){
        this.accountId = event.accountId();
        this.accountNumber = event.accountNumber();
        this.accountHolder = event.accountHolder();
        this.customerId = event.customerId();
        this.accountTypeCode = event.accountTypeCode();
        this.branchId = event.branchId();
        this.balance = event.initialBalance();
        this.status = event.status();
        this.createdAt = event.createdAt();
        this.createdBy = event.createdBy();
    }

    @CommandHandler
    public void handle(DepositMoneyCommand cmd){
        validateAccountFrozen();
        this.balance.checkSameCurrency(cmd.amount());
        validateMoneyNotZero(cmd.amount());
        validateAccountOwner(cmd.customerId());
        Money newBalance = new Money(
                this.balance.amount().add(cmd.amount().amount()),
                cmd.amount().currency()
        );
        AggregateLifecycle.apply(
                new MoneyDepositedEvent(
                        cmd.accountId(),
                        cmd.customerId(),
                        cmd.transactionId(),
                        cmd.amount(),
                        newBalance,
                        cmd.remark(),
                        ZonedDateTime.now()
                )
        );
    }

    @EventSourcingHandler
    protected void on(MoneyDepositedEvent event){
        this.balance = event.newBalance();
        this.updatedAt = event.createdAt();
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand cmd){
        validateAccountFrozen();
        this.balance.checkSameCurrency(cmd.amount());
        validateMoneyNotZero(cmd.amount());
        validateAccountOwner(cmd.customerId());
        if(this.balance.isLessThan(cmd.amount())){
            throw new AccountDomainException("Balance is not enough");
        }
        Money newBalance = new Money(
                this.balance.amount().subtract(cmd.amount().amount()),
                cmd.amount().currency()
        );
        AggregateLifecycle.apply(
                new MoneyWithdrawnEvent(
                        cmd.accountId(),
                        cmd.customerId(),
                        cmd.transactionId(),
                        cmd.amount(),
                        newBalance,
                        cmd.remark(),
                        ZonedDateTime.now()
                )
        );
    }

    @EventSourcingHandler
    protected void on(MoneyWithdrawnEvent event){
        this.balance = event.newBalance();
        this.updatedAt = event.createdAt();
    }

    @CommandHandler
    public void handle(FreezeAccountCommand cmd){
        validateAccountFrozen();
        AggregateLifecycle.apply(
                new AccountFrozenEvent(
                        cmd.accountId(),
                        this.customerId,
                        this.status,
                        AccountStatus.FREEZE,
                        cmd.remark(),
                        cmd.requestedBy(),
                        ZonedDateTime.now()
                )
        );
    }

    @EventSourcingHandler
    protected void on(AccountFrozenEvent event){
        this.status = event.newStatus();
        this.updatedAt = event.createdAt();
    }

    private String resolveCustomerName(CustomerName name){
        if(name != null){
            return name.familyName() + " " + name.givenName();
        }
        return "";
    }

    private void validateAccountType(AccountTypeCode accountTypeCode) {
        if (!accountTypeCode.equals(AccountTypeCode.SAVING)) {
            throw new AccountDomainException("Account type can only be saving");
        }
    }

    private void validateInitialBalance(Money initialBalance){
        Money minInitialBalance = new Money(new BigDecimal("10"), Currency.USD);
        if(initialBalance.isLessThan(minInitialBalance)){
            throw new AccountDomainException("Create new account is required at least 10 dollars initial");
        }
    }

    private void validateAccountOwner(CustomerId customerId){
        if(!this.customerId.customerId().equals(customerId.customerId())){
            throw new AccountDomainException("Customer does not own this account");
        }
    }

    private void validateMoneyNotZero(Money amount){
        if(amount.isZero()){
            throw new AccountDomainException("Amount can not be zero");
        }
    }

    private void validateAccountFrozen(){
        if(this.status.equals(AccountStatus.FREEZE)){
            throw new AccountDomainException("This account has ben frozen");
        }
    }


}
