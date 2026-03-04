package co.istad.itpaccountservice.data.repository;

import co.istad.itpaccountservice.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
}
