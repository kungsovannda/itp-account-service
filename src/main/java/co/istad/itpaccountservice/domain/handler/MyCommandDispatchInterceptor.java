package co.istad.itpaccountservice.domain.handler;

import co.istad.itpaccountservice.data.entity.CustomerEntity;
import co.istad.itpaccountservice.data.repository.AccountTypeRepository;
import co.istad.itpaccountservice.data.repository.BranchRepository;
import co.istad.itpaccountservice.data.repository.CustomerRepository;
import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.rest.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyCommandDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final WebClient.Builder webClientBuilder;
    private final BranchRepository branchRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CustomerRepository customerRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            log.info("Dispatch command interceptor: {}", command);
            if(command.getPayload() instanceof CreateAccountCommand createAccountCommand){
                validateCreateAccountCommand(createAccountCommand);
            }
            return command;

        };
    }

    private boolean checkLocalCustomer(UUID id){
        return customerRepository.existsById(id);
    }

    private void validateCreateAccountCommand(CreateAccountCommand cmd){
        WebClient webClient =  webClientBuilder.baseUrl("http://localhost:20260/api/customers/").build();

        CustomerResponse customerResponse = webClient.get()
                .uri("{customerId}", cmd.customerId().customerId())
                .retrieve().bodyToMono(CustomerResponse.class).block();

        branchRepository.findById(cmd.branchId().branchId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found")
        );

        accountTypeRepository.findByAccountTypeCode(cmd.accountTypeCode()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account type not found")
        );

        if(customerResponse != null) {
            CustomerEntity customer = new CustomerEntity();
            customer.setCustomerName(customerResponse.name());
            customer.setCustomerId(customerResponse.customerId());
            customer.setPhoneNumber(customerResponse.phoneNumber());
            customerRepository.save(customer);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
}