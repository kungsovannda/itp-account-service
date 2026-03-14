package co.istad.itpaccountservice.application.ports.output.repository;

import co.istad.itpaccountservice.dataaccess.entity.BranchEntity;

import java.util.Optional;
import java.util.UUID;

public interface BranchRepository {

    Optional<BranchEntity> findById(UUID id);

}
