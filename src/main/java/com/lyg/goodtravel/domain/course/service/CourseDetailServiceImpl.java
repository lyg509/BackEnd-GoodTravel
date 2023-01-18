package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.bean.CourseDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTagDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTourTestResultDetail;
import com.lyg.goodtravel.domain.course.db.bean.CourseTouristDetail;
import com.lyg.goodtravel.domain.course.db.entity.CourseData;
import com.lyg.goodtravel.domain.course.db.repository.BookmarkRepository;
import com.lyg.goodtravel.domain.course.db.repository.CourseDetailRepositorySpp;
import com.lyg.goodtravel.domain.course.db.repository.CourseRepository;
import com.lyg.goodtravel.domain.record.db.entity.Record;
import com.lyg.goodtravel.domain.record.db.repository.TourRepository;
import com.lyg.goodtravel.domain.record.db.repository.TourStampRepository;
import com.lyg.goodtravel.domain.tourtest.repository.TourTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TourTestRepository tourTestRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    TourStampRepository tourStampRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    CourseDetailRepositorySpp courseDetailRepositorySpp;


    @Override
    public List<CourseDetail> courseDetail(int courseId) { return courseDetailRepositorySpp.courseDetailByCourseId(courseId);}


    @Override
    public List<CourseTouristDetail> courseDataDetail(int courseId) {return courseDetailRepositorySpp.courseDataDetailByCourseId(courseId);}


    @Override
    public List<Record> courseRecordDetail(int courseId) { return courseDetailRepositorySpp.courseRecordDetailByCourseId(courseId); }


    @Override
    public List<CourseTourTestResultDetail> courseTourTestResultDetail(int courseId) { return courseDetailRepositorySpp.courseTourTestResultDetailByCourseId(courseId); }


    @Override
    public List<CourseTagDetail> courseTagDetail(int courseId) { return courseDetailRepositorySpp.courseTagDetailByCourseId(courseId); }


    @Override
    public double courseConnection(int courseId) {
        double tourStartCount, stampCount, bookmarkCount;

        // 코스 시작
        if(tourRepository.countToursByCourseId(courseId) != 0
                && tourRepository.countTour() != 0) {
            tourStartCount = ((double) tourRepository.countToursByCourseId(courseId) /
                    (double) tourRepository.countTour()) * 50;
        } else tourStartCount = 0;

        // 코스 관광지 방문
        if(tourStampRepository.countTourStampByCourseId(courseId) != 0
                && tourStampRepository.countTourStamp(courseId) != 0) {
            stampCount = ((double) tourStampRepository.countTourStampByCourseId(courseId) /
                    (double) tourStampRepository.countTourStamp(courseId)) * 35;
        } else stampCount = 0;

        // 코스 북마크
        if(bookmarkRepository.countBookmarksByCourseId(courseId) != 0
                && bookmarkRepository.countBookmarks() != 0) {
            bookmarkCount = ((double) bookmarkRepository.countBookmarksByCourseId(courseId) /
                    (double) bookmarkRepository.countBookmarks()) * 15;
        } else bookmarkCount = 0;

        double connectionPer = Math.round(tourStartCount + stampCount + bookmarkCount);

        return connectionPer;
    }
}