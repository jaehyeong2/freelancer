package jjfactory.freelancer.application;

import jjfactory.freelancer.domain.FreelancerInfo;
import jjfactory.freelancer.domain.FreelancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FreelancerFacade {
    private final FreelancerService freelancerService;

    public Page<FreelancerInfo.List> findFreelancerPage(Pageable pageable){
        return freelancerService.findPage(pageable);
    }
}
