package org.example.worker.vo;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ManagerVO {
    private Integer mno;
    private String mid;
    private String mpw;
    private String dept;
    private boolean mdelflag;
}
