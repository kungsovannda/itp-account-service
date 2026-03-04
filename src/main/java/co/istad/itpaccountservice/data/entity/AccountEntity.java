package co.istad.itpaccountservice.data.entity;

import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import co.istad.itpcommon.domain.valueobject.AccountStatus;
import co.istad.itpcommon.domain.valueobject.Money;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
@Transactional
public class AccountEntity {

    @Id
    private UUID accountId;
    private UUID customerId;
    private UUID branchId;
    private String accountNumber;
    private String accountHolder;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode accountTypeCode;

    @Embedded
    private Money balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;


    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountTypeEntity accountType;

}
