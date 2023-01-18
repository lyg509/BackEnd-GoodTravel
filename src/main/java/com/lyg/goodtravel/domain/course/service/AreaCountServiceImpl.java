package com.lyg.goodtravel.domain.course.service;

import com.lyg.goodtravel.domain.course.db.entity.AreaCount;
import com.lyg.goodtravel.domain.course.db.repository.AreaCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaCountServiceImpl implements AreaCountService {

    @Autowired
    AreaCountRepository areaCountRepository;
    @Override
    public List<AreaCount> searchAll() {
        return areaCountRepository.findAll();
    }
}