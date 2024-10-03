package jjfactory.freelancer.application;

import jjfactory.freelancer.domain.FreelancerCommand;
import jjfactory.freelancer.domain.FreelancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FreelancerFacade {
    private final FreelancerService freelancerService;

    public Long join(FreelancerCommand.Create command){
        return freelancerService.store(command);
    }
}
