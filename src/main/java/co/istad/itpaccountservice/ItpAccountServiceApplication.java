package co.istad.itpaccountservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("public/test")
@SpringBootApplication
public class ItpAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItpAccountServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(
//            @Value("${service.name}") String name,
//            @Value("${secret.weak-password}") String weakPassword,
//            @Value("${secret.strong-password}") String strongPassword
//
//    ){
//        return args -> {
//            System.out.println("Service name: " + name);
//            System.out.println("Strong password: " + strongPassword);
//            System.out.println("Weak password: " + weakPassword);
//        };
//    }

    @GetMapping
    public Map<String, String> getAccounts(){
        return Map.of(
                "data", "Account: Hello From Account"
        );
    }

}

