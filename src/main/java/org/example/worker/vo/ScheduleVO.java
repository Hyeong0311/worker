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
    private String wid;
    private Timestamp in;
    private Timestamp out;
    private String note;
}
