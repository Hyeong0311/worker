package org.example.worker.vo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryCheckDTO {

    private String wname;
    private Timestamp in;
    private Timestamp out;
    private String dept;
    private int wid;
}
