package co.istad.itpaccountservice.domain.aggregate;

import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.domain.event.AccountCreatedEvent;
import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

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
    public AccountAggregate(CreateAccountCommand cmd){
        log.info("Received CMD: {}",cmd);
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        cmd.accountId(),
                        cmd.accountNumber(),
                        cmd.accountHolder(),
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


}
