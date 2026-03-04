package co.istad.itpaccountservice.data.repository;

import co.istad.itpaccountservice.data.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
}
