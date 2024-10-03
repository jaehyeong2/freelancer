package jjfactory.freelancer.domain;

import lombok.Builder;

public class FreelancerInfo {

    @Builder
    public record Detail(
            Long id,
            String lastName,
            String firstName,
            String phone
    ){
    }
}
