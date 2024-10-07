package jjfactory.freelancer.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jjfactory.freelancer.domain.Freelancer;
import jjfactory.freelancer.domain.FreelancerInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class FreelancerDslRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    private FreelancerDslRepository freelancerDslRepository;

    @BeforeEach
    void setUp() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        freelancerDslRepository = new FreelancerDslRepository(queryFactory);

        Freelancer freelancer1 = Freelancer.builder()
                .lastName("가")
                .firstName("가")
                .viewCount(100L)
                .isExposed(true)
                .exposedAt(LocalDateTime.now().minusDays(1))
                .build();

        Freelancer freelancer2 = Freelancer.builder()
                .lastName("가")
                .firstName("나")
                .viewCount(200L)
                .isExposed(true)
                .exposedAt(LocalDateTime.now().minusDays(2))
                .build();

        Freelancer freelancer3 = Freelancer.builder()
                .lastName("나")
                .firstName("나")
                .viewCount(300L)
                .isExposed(true)
                .exposedAt(LocalDateTime.now().minusDays(3))
                .build();

        Freelancer freelancer4 = Freelancer.builder()
                .lastName("가")
                .firstName("다")
                .exposedAt(LocalDateTime.now().minusDays(3))
                .build();


        entityManager.persist(freelancer1);
        entityManager.persist(freelancer2);
        entityManager.persist(freelancer3);
        entityManager.persist(freelancer4);
    }

    @DisplayName("isExposed가 트루인 인원만 조회된다.")
    @Test
    void getFreelancers() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("name")));
        Page<FreelancerInfo.List> page = freelancerDslRepository.getFreelancerInfoPages(pageable);

        List<FreelancerInfo.List> freelancers = page.getContent();
        assertThat(freelancers).hasSize(3);
    }

    @DisplayName("이름 asc 정렬 시 가나다 순으로 정렬된다")
    @Test
    void sortByNameAsc() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("name")));
        Page<FreelancerInfo.List> page = freelancerDslRepository.getFreelancerInfoPages(pageable);

        List<FreelancerInfo.List> freelancers = page.getContent();

        assertThat(freelancers).hasSize(3);
        assertThat(freelancers.get(0).getKoreanName()).isEqualTo("가가");
        assertThat(freelancers.get(1).getKoreanName()).isEqualTo("가나");
        assertThat(freelancers.get(2).getKoreanName()).isEqualTo("나나");
    }

    @DisplayName("조회수 순 정렬 시 조회수 순으로 조회된다")
    @Test
    void sortByViewCountDesc() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("viewCount")));
        Page<FreelancerInfo.List> page = freelancerDslRepository.getFreelancerInfoPages(pageable);

        List<FreelancerInfo.List> freelancers = page.getContent();

        assertThat(freelancers).hasSize(3);
        assertThat(freelancers.get(0).getViewCount()).isEqualTo(300);
        assertThat(freelancers.get(1).getViewCount()).isEqualTo(200);
        assertThat(freelancers.get(2).getViewCount()).isEqualTo(100);
    }

    @DisplayName("등록일 최신 순 정렬 시 등록일 순으로 조회된다")
    @Test
    void sortByExposedAtDesc() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("exposedAt")));
        Page<FreelancerInfo.List> page = freelancerDslRepository.getFreelancerInfoPages(pageable);

        List<FreelancerInfo.List> freelancers = page.getContent();

        assertThat(freelancers).hasSize(3);
        assertThat(freelancers.get(0).getExposedAt()).isAfter(freelancers.get(1).getExposedAt());
        assertThat(freelancers.get(1).getExposedAt()).isAfter(freelancers.get(2).getExposedAt());
    }

}