package co.istad.itpaccountservice.dataaccess.repository;

import co.istad.itpaccountservice.dataaccess.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
