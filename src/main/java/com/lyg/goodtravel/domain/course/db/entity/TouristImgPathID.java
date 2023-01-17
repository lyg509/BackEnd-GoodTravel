package com.lyg.goodtravel.domain.course.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristImgPathID implements Serializable {
    @Column(name = "file_id")
    private int fileId;

    @Column(name = "tourist_id")
    private int touristId;
}
