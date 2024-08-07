package org.example.worker.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupervisorDTO {

    private String wname;
    private String category;
    private int wid;
}
