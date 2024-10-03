package jjfactory.freelancer.infrastructure;

import jjfactory.freelancer.domain.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
}
