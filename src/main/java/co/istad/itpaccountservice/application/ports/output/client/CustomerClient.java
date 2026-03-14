package co.istad.itpaccountservice.application.ports.output.client;

import co.istad.itpaccountservice.application.dto.customer.CustomerResponse;

public interface CustomerClient {

    CustomerResponse getCustomer(String id);
}
