package jjfactory.freelancer.presentation;

import jjfactory.freelancer.domain.FreelancerCommand;
import org.springframework.stereotype.Component;

@Component
public class FreelancerMapper {
    public FreelancerCommand.Create convert(FreelancerDto.Create dto){
        return FreelancerCommand.Create
                .builder()
                .lastName(dto.lastName())
                .firstName(dto.firstName())
                .phone(dto.phone())
                .build();
    }
}
