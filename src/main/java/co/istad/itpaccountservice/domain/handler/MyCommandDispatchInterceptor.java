package co.istad.itpaccountservice.domain.handler;

import co.istad.itpaccountservice.application.dto.customer.CustomerResponse;
import co.istad.itpaccountservice.application.ports.output.client.CustomerClient;
import co.istad.itpaccountservice.domain.command.CreateAccountCommand;
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
        if(customerResponse != null) {
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
}