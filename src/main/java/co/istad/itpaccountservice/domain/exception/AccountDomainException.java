package co.istad.itpaccountservice.domain.exception;

import co.istad.itpcommon.domain.exception.DomainException;

public class AccountDomainException extends DomainException {
    public AccountDomainException(String message) {
        super(message);
    }
}
