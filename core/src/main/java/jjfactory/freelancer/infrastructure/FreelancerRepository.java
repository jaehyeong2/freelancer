package jjfactory.freelancer.infrastructure;

import jjfactory.freelancer.domain.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    Page<Freelancer> findAllByOrderByViewCountDesc(Pageable pageable);
}
