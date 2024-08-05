package org.example.worker.vo;


import lombok.*;

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
    private String mid;
}
