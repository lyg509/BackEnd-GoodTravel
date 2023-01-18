package com.lyg.goodtravel.domain.user.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DateAnalysisDetail {
    private int yearAndMonth;
    private Long dateCount;
}