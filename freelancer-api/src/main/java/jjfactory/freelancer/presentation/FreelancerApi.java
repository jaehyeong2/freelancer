package jjfactory.freelancer.presentation;

import jjfactory.freelancer.application.FreelancerFacade;
import jjfactory.freelancer.domain.FreelancerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/freelancers")
@RestController
public class FreelancerApi {
    private final FreelancerFacade freelancerFacade;
    private final FreelancerMapper freelancerMapper;

    @PostMapping
    public Long join(@RequestBody FreelancerDto.Create dto) {
        FreelancerCommand.Create command = freelancerMapper.convert(dto);
        return freelancerFacade.join(command);
    }
}
