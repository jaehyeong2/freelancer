package jjfactory.freelancer.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FreelancerTest {

    @DisplayName("한국 이름 조회시 성이름 순서로 합쳐서 리턴된다")
    @Test
    public void getFullName(){
        Freelancer freelancer = Freelancer.builder().lastName("lee")
                .firstName("jj")
                .build();

        assertThat(freelancer.getKoreanName()).isEqualTo("leejj");
    }

    @DisplayName("프로필 노출 시 노출시간이 할당되고 노출여부가 트루가 된다.")
    @Test
    public void exposeSuccess(){
        Freelancer freelancer = Freelancer.builder().lastName("lee")
                .firstName("jj")
                .build();

        freelancer.exposeProfile();

        assertThat(freelancer.isExposed()).isTrue();
        assertThat(freelancer.getExposedAt()).isNotNull();
    }

}