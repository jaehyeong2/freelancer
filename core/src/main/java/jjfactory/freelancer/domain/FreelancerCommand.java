package jjfactory.freelancer.domain;

import lombok.Builder;

public class FreelancerCommand {

    @Builder
    public record Create(
            String lastName,
            String firstName,
            String phone
    ){
        public Freelancer toEntity(){
            return Freelancer.builder()
                    .lastName(lastName)
                    .firstName(firstName)
                    .phone(phone)
                    .build();
        }

    }
}
