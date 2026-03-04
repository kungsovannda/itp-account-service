package co.istad.itpaccountservice.data.init;

import co.istad.itpaccountservice.data.entity.BranchEntity;
import co.istad.itpaccountservice.data.repository.BranchRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitBranch {

    private final BranchRepository branchRepository;

    @PostConstruct
    public void init(){
        if(branchRepository.count() == 0){
            BranchEntity pursat = new BranchEntity();
            pursat.setBranchName("Pursat");
            pursat.setIsOpened(true);
            pursat.setId(UUID.randomUUID());

            BranchEntity phnomPenh = new BranchEntity();
            phnomPenh.setBranchName("Phnom Penh");
            phnomPenh.setIsOpened(true);
            phnomPenh.setId(UUID.randomUUID());

            branchRepository.saveAll(
                    List.of(pursat, phnomPenh)
            );
        }

    }
}
