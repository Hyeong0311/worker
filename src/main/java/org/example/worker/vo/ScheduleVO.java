package org.example.worker.vo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ScheduleVO {
    private Integer scno;
    private Integer wid;
    private Timestamp time;
    private String category;
    private String note;
}
