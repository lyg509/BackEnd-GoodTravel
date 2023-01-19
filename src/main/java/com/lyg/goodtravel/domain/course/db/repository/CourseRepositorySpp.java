package com.lyg.goodtravel.domain.course.db.repository;

import com.lyg.goodtravel.domain.course.db.bean.BookmarkCourse;
import com.lyg.goodtravel.domain.course.db.bean.CourseSearch;
import com.lyg.goodtravel.domain.course.db.bean.PopularCourse;
import com.lyg.goodtravel.domain.course.db.entity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositorySpp {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QCourse qCourse = QCourse.course;
    QCourseData qCourseData = QCourseData.courseData;
    QTouristImgPath qTouristImgPath = QTouristImgPath.touristImgPath;
    QBookmark qBookmark = QBookmark.bookmark;


    // 북마크한 코스 조회
    public List<BookmarkCourse> findBookmarkCourse(int userId) {
        return jpaQueryFactory
                .select(Projections.constructor(BookmarkCourse.class,
                        qCourse.courseId,
                        qCourse.courseName,
                        qCourseData.touristId,
                        qTouristImgPath.fileId.min().as("fileId")))
                .from(qCourse)
                .leftJoin(qCourseData).on(qCourseData.courseId.eq(qCourse.courseId))
                .leftJoin(qBookmark).on(qBookmark.courseId.eq(qCourse.courseId))
                .leftJoin(qTouristImgPath).on(qTouristImgPath.touristId.eq(qCourseData.touristId))
                .where(qCourseData.courseDataId.eq(1).and(qBookmark.userId.eq(userId)))
                .groupBy(qCourse.courseId)
                .fetch();
    }

    // 인기 있는 코스
    public Page<PopularCourse> findPopularCourse (Pageable pageable) {
        QueryResults<PopularCourse> list = jpaQueryFactory
                .select(Projections.constructor(PopularCourse.class,
                        qCourse.courseId,
                        qCourse.courseName,
                        qCourseData.touristId,
                        qTouristImgPath.fileId.min().as("fileId")))
                .from(qCourse)
                .leftJoin(qCourseData).on(qCourseData.courseId.eq(qCourse.courseId))
                .leftJoin(qTouristImgPath).on(qTouristImgPath.touristId.eq(qCourseData.touristId))
                .leftJoin(qBookmark).on(qBookmark.courseId.eq(qCourse.courseId))
                .where(qCourseData.courseDataId.eq(1))
                .groupBy(qCourse.courseId)
                .orderBy(qCourse.courseHits.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        return new PageImpl<>(list.getResults(), pageable, list.getTotal());
    }

    // 코스 검색
    // 코스 검색
    public Page<CourseSearch> findCourseSearch (String courseName, Pageable pageable) {
        QueryResults<CourseSearch> list = jpaQueryFactory.select(Projections.constructor(CourseSearch.class, qCourse.courseId, qCourse.courseName,
                        qCourseData.touristId, qTouristImgPath.fileId.min().as("fileId"))).from(qCourse)
                .leftJoin(qCourseData).on(qCourseData.courseId.eq(qCourse.courseId))
                .leftJoin(qTouristImgPath).on(qTouristImgPath.touristId.eq(qCourseData.touristId))
                .where(qCourse.courseName.contains(courseName).and(qCourseData.courseDataId.eq(1)))
                .groupBy(qCourse.courseId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        return new PageImpl<>(list.getResults(), pageable, list.getTotal());
    }

    public Page<Course> findCourseListByUser (int userId, Pageable pageable) {
        QueryResults<Course> list = jpaQueryFactory.select(qCourse).from(qCourse)
                .where(qCourse.userId.eq(userId).and(qCourse.isRegister.eq(true)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        return new PageImpl<>(list.getResults(), pageable, list.getTotal());
    }
}