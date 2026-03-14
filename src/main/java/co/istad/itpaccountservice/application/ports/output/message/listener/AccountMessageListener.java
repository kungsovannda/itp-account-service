package co.istad.itpaccountservice.application.ports.output.message.listener;

import co.istad.itpcommon.domain.event.AccountCreatedEvent;

public interface AccountMessageListener {

    void on(AccountCreatedEvent event);

}
