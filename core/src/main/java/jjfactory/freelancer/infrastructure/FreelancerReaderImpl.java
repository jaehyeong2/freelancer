package jjfactory.freelancer.infrastructure;

import jjfactory.freelancer.domain.Freelancer;
import jjfactory.freelancer.domain.FreelancerInfo;
import jjfactory.freelancer.domain.FreelancerNotFoundException;
import jjfactory.freelancer.domain.FreelancerReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FreelancerReaderImpl implements FreelancerReader {
    private final FreelancerRepository freelancerRepository;
    @Override
    public Optional<Freelancer> findById(Long id) {
        return freelancerRepository.findById(id);
    }

    @Override
    public Freelancer findByIdOrThrow(Long id) {
        return freelancerRepository.findById(id).orElseThrow(FreelancerNotFoundException::new);
    }

    @Override
    public Page<FreelancerInfo.Detail> findPage(Pageable pageable) {
        return null;
    }
}
