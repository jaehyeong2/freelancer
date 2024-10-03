package jjfactory.freelancer.infrastructure;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.freelancer.domain.FreelancerInfo;
import jjfactory.freelancer.domain.QFreelancer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.freelancer.domain.QFreelancer.*;

@RequiredArgsConstructor
@Repository
public class FreelancerDslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<FreelancerInfo.Detail> getFreelancers(Pageable pageable){
        List<FreelancerInfo.Detail> result = queryFactory.select(Projections.fields(FreelancerInfo.Detail.class,
                        freelancer.id.as("id"),
                        freelancer.lastName.as("lastName"),
                        freelancer.firstName.as("firstName"),
                        freelancer.phone.as("phone")
                ))
                .from(freelancer)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(freelancer.count())
                .from(freelancer)
                .fetchOne();


        return new PageImpl<>(result, pageable, count);
    }

}
