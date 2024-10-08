package jjfactory.freelancer.application;

import jjfactory.freelancer.domain.FreelancerInfo;
import jjfactory.freelancer.domain.FreelancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static jjfactory.freelancer.common.config.cache.CacheType.TOP_FREELANCERS_KEY;

@RequiredArgsConstructor
@Component
public class FreelancerFacade {
    private final FreelancerService freelancerService;
    private final RedisTemplate<String, String> redisTemplate;

    public Page<FreelancerInfo.List> findFreelancerPage(Pageable pageable){
        return freelancerService.findPage(pageable);
    }

    public FreelancerInfo.Detail findFreelancerById(Long id){
        Double score = redisTemplate.opsForZSet().score(TOP_FREELANCERS_KEY.getCacheName(), id.toString());

        FreelancerInfo.Detail freelancer = freelancerService.findById(id);
        return freelancer;
    }
}
