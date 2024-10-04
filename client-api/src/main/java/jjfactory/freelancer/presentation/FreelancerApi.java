package jjfactory.freelancer.presentation;

import jjfactory.freelancer.application.FreelancerFacade;
import jjfactory.freelancer.common.response.PageResponse;
import jjfactory.freelancer.domain.FreelancerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/freelancers")
@RestController
public class FreelancerApi {
    private final FreelancerFacade freelancerFacade;

    @GetMapping
    public PageResponse<FreelancerInfo.List> findFreelancers(@PageableDefault Pageable pageable){
        return new PageResponse<>(freelancerFacade.findFreelancerPage(pageable), pageable);
    }
}
