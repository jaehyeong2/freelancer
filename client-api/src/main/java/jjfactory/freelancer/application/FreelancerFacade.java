package jjfactory.freelancer.application;

import jjfactory.freelancer.domain.FreelancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FreelancerFacade {
    private final FreelancerService freelancerService;


}
