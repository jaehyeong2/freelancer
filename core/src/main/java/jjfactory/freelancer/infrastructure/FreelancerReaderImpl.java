package jjfactory.freelancer.infrastructure;

import jjfactory.freelancer.domain.Freelancer;
import jjfactory.freelancer.domain.FreelancerInfo;
import jjfactory.freelancer.domain.FreelancerNotFoundException;
import jjfactory.freelancer.domain.FreelancerReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FreelancerReaderImpl implements FreelancerReader {
    private final FreelancerRepository freelancerRepository;
    private final FreelancerDslRepository freelancerDslRepository;
    @Override
    public Optional<Freelancer> findById(Long id) {
        return freelancerRepository.findById(id);
    }

    @Override
    public Freelancer findByIdOrThrow(Long id) {
        return freelancerRepository.findById(id).orElseThrow(FreelancerNotFoundException::new);
    }

    @Override
    public Page<FreelancerInfo.List> findPage(Pageable pageable) {
        return freelancerDslRepository.getFreelancerInfoPages(pageable);
    }

    @Override
    public Page<Freelancer> findTopFreelancersByViewCount(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return freelancerRepository.findAllByOrderByViewCountDesc(pageable);
    }
}
