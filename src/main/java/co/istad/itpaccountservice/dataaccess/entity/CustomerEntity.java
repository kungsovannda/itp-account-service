package co.istad.itpaccountservice.dataaccess.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import co.istad.itpcommon.domain.valueobject.CustomerName;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
@Transactional
public class CustomerEntity {

    @Id
    private UUID customerId;

    @Embedded
    private CustomerName customerName;

    private String phoneNumber;
}
