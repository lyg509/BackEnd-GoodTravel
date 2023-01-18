package com.lyg.goodtravel.domain.record.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourTagList {
    private String codeName;
    private int tagId;
    private String tagName;
}
