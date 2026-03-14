package co.istad.itpaccountservice.domain.handler;

import co.istad.itpaccountservice.application.ports.output.client.CustomerClient;
import co.istad.itpaccountservice.application.ports.output.repository.AccountTypeRepository;
import co.istad.itpaccountservice.application.ports.output.repository.BranchRepository;
import co.istad.itpaccountservice.application.ports.output.repository.CustomerRepository;
import co.istad.itpaccountservice.dataaccess.entity.CustomerEntity;
import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
import co.istad.itpaccountservice.application.dto.customer.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyCommandDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final CustomerClient customerClient;
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

    private void validateCreateAccountCommand(CreateAccountCommand cmd){

        CustomerResponse customerResponse = customerClient.getCustomer(cmd.customerId().customerId().toString());

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