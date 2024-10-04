package jjfactory.freelancer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
        private Long viewCount;
        private LocalDateTime exposedAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class List {
        private Long id;
        private String lastName;
        private String firstName;
        private Long viewCount;
        private LocalDateTime exposedAt;

        public String getKoreanName(){
            return lastName + firstName;
        }
    }
}
