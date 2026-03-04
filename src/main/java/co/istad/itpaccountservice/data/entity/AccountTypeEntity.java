package co.istad.itpaccountservice.data.entity;


import co.istad.itpaccountservice.domain.valueobject.AccountTypeCode;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accountTypes")
@Transactional
public class AccountTypeEntity {

    @Id
    private UUID accountTypeId;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode accountTypeCode;

    @OneToMany(mappedBy = "accountType")
    private List<AccountEntity> accounts;
}
