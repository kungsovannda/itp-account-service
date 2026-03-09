package co.istad.itpaccountservice.domain.validate;

import co.istad.itpaccountservice.domain.exception.AccountDomainException;
import org.springframework.stereotype.Component;

public class AccountValidate {

    public static void validateAccountNumber(String accountNumber){
        if(accountNumber.isBlank()){
            throw new AccountDomainException("Account number can not be null or empty");
        }
        if(accountNumber.length() != 9){
            throw new AccountDomainException("Account number must be 9 digits");
        }
        if (!accountNumber.matches("\\d+")) {
            throw new AccountDomainException("Account number must contain only digits");
        }
    }
}
