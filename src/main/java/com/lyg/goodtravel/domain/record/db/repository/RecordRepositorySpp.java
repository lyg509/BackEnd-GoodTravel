package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.QRecord;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QRecord qRecord = QRecord.record;

}