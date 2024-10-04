package jjfactory.freelancer.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FreelancerReader {
    Optional<Freelancer> findById(Long id);
    Freelancer findByIdOrThrow(Long id);
    Page<FreelancerInfo.List> findPage(Pageable pageable);
}
