package co.istad.itpaccountservice.domain.command;

import co.istad.itpcommon.domain.valueobject.AccountId;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record FreezeAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String remark,
        String requestedBy
) {
}
