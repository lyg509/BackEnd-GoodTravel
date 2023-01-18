package com.lyg.goodtravel.domain.record.db.repository;

import com.lyg.goodtravel.domain.record.db.entity.TagCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagCodeRepository extends JpaRepository<TagCode, Integer> {

    @Query("select t from TagCode t")
    List<TagCode> findTagList();

}
