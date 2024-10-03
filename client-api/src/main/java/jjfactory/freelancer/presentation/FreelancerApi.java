package jjfactory.freelancer.presentation;

import jjfactory.freelancer.application.FreelancerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/freelancers")
@RestController
public class FreelancerApi {
    private final FreelancerFacade freelancerFacade;


}
