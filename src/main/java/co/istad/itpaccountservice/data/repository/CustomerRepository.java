package co.istad.itpaccountservice.data.repository;

import co.istad.itpaccountservice.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
