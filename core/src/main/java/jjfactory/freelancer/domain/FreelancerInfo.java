package jjfactory.freelancer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FreelancerInfo {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Detail {
        private Long id;
        private String lastName;
        private String firstName;
        private String phone;
    }
}
