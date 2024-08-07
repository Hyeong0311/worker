package org.example.worker.vo;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class WorkerVO {
    private Integer wno;
    private Integer wid;
    private String wname;
    private boolean wdelflag;
    private String sid;
}
