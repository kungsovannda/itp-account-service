package co.istad.itpaccountservice.dataaccess.repository;

import co.istad.itpaccountservice.dataaccess.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchJpaRepository extends JpaRepository<BranchEntity, UUID> {
}
