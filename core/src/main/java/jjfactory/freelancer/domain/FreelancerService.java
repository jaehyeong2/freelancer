package jjfactory.freelancer.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreelancerService {
    Long store(FreelancerCommand.Create command);

    Page<FreelancerInfo.List> findPage(Pageable pageable);

    FreelancerInfo.Detail findById(Long id);
}
