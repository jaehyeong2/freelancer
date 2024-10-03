package jjfactory.freelancer.domain;

import jjfactory.freelancer.infrastructure.FreelancerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FreelancerServiceImpl implements FreelancerService {
    private final FreelancerRepository freelancerRepository;

    @Transactional
    @Override
    public Long store(FreelancerCommand.Create command) {
        Freelancer initEntity = command.toEntity();

        return freelancerRepository.save(initEntity).getId();
    }
}
