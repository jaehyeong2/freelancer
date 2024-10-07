package jjfactory.freelancer.infrastructure;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.freelancer.domain.FreelancerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static jjfactory.freelancer.domain.QFreelancer.*;

@RequiredArgsConstructor
@Repository
public class FreelancerDslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<FreelancerInfo.List> getFreelancerInfoPages(Pageable pageable){
        List<OrderSpecifier<?>> orderSpecifiers = getOrderSpecifiers(pageable);

        List<FreelancerInfo.List> result = queryFactory.select(Projections.fields(FreelancerInfo.List.class,
                        freelancer.id.as("id"),
                        freelancer.lastName.as("lastName"),
                        freelancer.firstName.as("firstName"),
                        freelancer.viewCount.as("viewCount"),
                        freelancer.exposedAt.as("exposedAt")
                ))
                .from(freelancer)
                .where(freelancer.isExposed.isTrue())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
                .fetch();

        Long count = queryFactory.select(freelancer.count())
                .from(freelancer)
                .where(freelancer.isExposed.isTrue())
                .fetchOne();


        return new PageImpl<>(result, pageable, count);
    }

    private List<OrderSpecifier<?>> getOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        for (Sort.Order order : pageable.getSort()) {
            switch (order.getProperty()) {
                case "name" -> {
                    StringTemplate fullName = Expressions.stringTemplate("CONCAT({0}, ' ', {1})", freelancer.lastName, freelancer.firstName);
                    orderSpecifiers.add(getSortedColumn(order, fullName));
                }
                case "viewCount" -> orderSpecifiers.add(getSortedColumn(order, freelancer.viewCount));
                case "exposedAt" -> orderSpecifiers.add(getSortedColumn(order, freelancer.exposedAt));
                default -> {
                }
            }
        }

        return orderSpecifiers;
    }

    private OrderSpecifier<?> getSortedColumn(Sort.Order order, ComparableExpressionBase<?> column) {
        return order.isAscending() ? column.asc() : column.desc();
    }

}
