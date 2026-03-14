package co.istad.itpaccountservice.domain.handler;

import co.istad.itpaccountservice.application.ports.output.message.listener.AccountMessageListener;
import co.istad.itpcommon.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountListener {

    private final AccountMessageListener accountMessageListener;

    @EventHandler
    public void on(AccountCreatedEvent event){
        accountMessageListener.on(event);
    }
}
