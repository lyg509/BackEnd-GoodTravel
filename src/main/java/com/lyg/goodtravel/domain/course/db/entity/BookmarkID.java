package com.lyg.goodtravel.domain.course.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkID implements Serializable {
    private int courseId;
    private int userId;
}
