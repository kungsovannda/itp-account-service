package co.istad.itpaccountservice.dataaccess.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "branches")
@Transactional
public class BranchEntity {

    @Id
    private UUID id;
    private String branchName;
    private Boolean isOpened;
}
