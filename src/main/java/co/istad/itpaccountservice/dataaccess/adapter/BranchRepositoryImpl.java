package co.istad.itpaccountservice.dataaccess.adapter;

import co.istad.itpaccountservice.application.ports.output.repository.BranchRepository;
import co.istad.itpaccountservice.dataaccess.entity.BranchEntity;
import co.istad.itpaccountservice.dataaccess.repository.BranchJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BranchRepositoryImpl implements BranchRepository {

    private final BranchJpaRepository branchJpaRepository;

    @Override
    public Optional<BranchEntity> findById(UUID id) {
        return branchJpaRepository.findById(id);
    }
}
