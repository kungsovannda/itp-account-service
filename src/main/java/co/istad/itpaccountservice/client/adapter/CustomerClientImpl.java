package co.istad.itpaccountservice.client.adapter;

import co.istad.itpaccountservice.application.dto.customer.CustomerResponse;
import co.istad.itpaccountservice.application.ports.output.client.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CustomerClientImpl implements CustomerClient {

    private final WebClient.Builder builder;
    private final WebClient webClient = builder
            .baseUrl("http://customer/api/customers/")
            .build();

    @Override
    public CustomerResponse getCustomer(String id) {
        return webClient.get()
                .uri("{customerId}", id)
                .retrieve().bodyToMono(CustomerResponse.class)
                .block();
    }
}
