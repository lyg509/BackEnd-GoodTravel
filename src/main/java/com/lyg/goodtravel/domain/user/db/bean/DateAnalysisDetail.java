package com.lyg.goodtravel.domain.user.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DateAnalysisDetail implements Comparable<DateAnalysisDetail>{
    private int yearAndMonth;
    private Long dateCount;

    @Override
    public int compareTo(DateAnalysisDetail o) {
        return this.yearAndMonth - o.yearAndMonth;
    }
}