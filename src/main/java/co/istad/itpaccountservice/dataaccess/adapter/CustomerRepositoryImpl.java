package co.istad.itpaccountservice.dataaccess.adapter;

import co.istad.itpaccountservice.application.ports.output.repository.CustomerRepository;
import co.istad.itpaccountservice.dataaccess.entity.CustomerEntity;
import co.istad.itpaccountservice.dataaccess.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        return customerJpaRepository.save(customerEntity);
    }
}
