package org.example.worker.vo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HRListDTO {

    private String wname;
    private String category;
    private Timestamp time;
    private String dept;
    private String note;
    private int wid;
}
