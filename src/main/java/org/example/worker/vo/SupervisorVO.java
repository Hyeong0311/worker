package org.example.worker.vo;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupervisorVO {
    private Integer sno;
    private String sid;
    private String spw;
    private String dept;
    private boolean sdelflag;
}
