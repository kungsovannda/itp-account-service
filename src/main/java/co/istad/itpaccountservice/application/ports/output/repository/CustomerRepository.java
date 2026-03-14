package co.istad.itpaccountservice.application.ports.output.repository;

import co.istad.itpaccountservice.dataaccess.entity.CustomerEntity;

public interface CustomerRepository {

    CustomerEntity save(CustomerEntity customerEntity);
}
