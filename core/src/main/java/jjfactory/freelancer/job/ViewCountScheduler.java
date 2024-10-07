package jjfactory.freelancer.job;

import jjfactory.freelancer.common.OperateNotificator;
import jjfactory.freelancer.domain.Freelancer;
import jjfactory.freelancer.domain.FreelancerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ViewCountScheduler {
    private final FreelancerReader freelancerReader;
    private final RedisTemplate<String, String> redisTemplate;
    private final OperateNotificator operateNotificator;

    private static final int CACHE_SIZE = 30;
    private static final String TOP_FREELANCERS_KEY = "top_view_freelancers";

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTopViewedUsersCache() {
        try{
            Page<Freelancer> freelancers = freelancerReader.findTopFreelancersByViewCount(CACHE_SIZE);

            redisTemplate.delete(TOP_FREELANCERS_KEY);

            freelancers.forEach(freelancer -> redisTemplate.opsForZSet().add(
                    TOP_FREELANCERS_KEY,
                    freelancer.getId().toString(),
                    freelancer.getViewCount()
            ));
        }catch (Exception e){
            log.error("Failed to update top freelancers cache", e);
            operateNotificator.sendErrorNotification();
        }

    }
}
