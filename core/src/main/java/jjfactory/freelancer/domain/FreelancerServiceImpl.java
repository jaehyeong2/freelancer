package jjfactory.freelancer.domain;

import jjfactory.freelancer.infrastructure.FreelancerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FreelancerServiceImpl implements FreelancerService {
    private final FreelancerRepository freelancerRepository;
    private final FreelancerReader freelancerReader;

    @Transactional
    @Override
    public Long store(FreelancerCommand.Create command) {
        Freelancer initEntity = command.toEntity();

        return freelancerRepository.save(initEntity).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<FreelancerInfo.List> findPage(Pageable pageable){
        return freelancerReader.findPage(pageable);
    }


    @Transactional(readOnly = true)
    @Override
    public FreelancerInfo.Detail findById(Long id){
        Freelancer freelancer = freelancerReader.findByIdOrThrow(id);
        freelancer.checkViewable();

        return FreelancerInfo.Detail.builder()
                .lastName(freelancer.getLastName())
                .firstName(freelancer.getFirstName())
                .exposedAt(freelancer.getExposedAt())
                .viewCount(freelancer.getViewCount())
                .phone(freelancer.getPhone())
                .build();
    }
}
