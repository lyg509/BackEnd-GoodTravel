package com.lyg.goodtravel.domain.tourtest.repository;

import com.lyg.goodtravel.domain.tourtest.db.entity.QTourTest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourTestRepositorySpp {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QTourTest qTourTest = QTourTest.tourTest;

    public List<Integer> findTourTestResult () {
        return jpaQueryFactory.select(qTourTest.tourTestResult).from(qTourTest)
                .groupBy(qTourTest.tourTestId)
                .where(qTourTest.tourTestId.lt(7))
                .fetchAll().fetch();
    }
}