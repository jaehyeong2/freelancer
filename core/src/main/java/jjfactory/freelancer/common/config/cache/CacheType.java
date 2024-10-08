package jjfactory.freelancer.common.config.cache;

import lombok.Getter;

@Getter
public enum CacheType {
    TOP_FREELANCERS_KEY("top_view_freelancers", 365*24*3600, 10000),
    FREELANCER_KEY("freelancer", 365*24*3600, 10000);


    private final String cacheName;
    private final int expiredTimeAsSec;
    private final int maximumSize;

    CacheType(String cacheName, int expiredTimeAsSec, int maximumSize) {
        this.cacheName = cacheName;
        this.expiredTimeAsSec = expiredTimeAsSec;
        this.maximumSize = maximumSize;
    }
}
