package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequesDTO {
    private String name;
    private  int kor ,eng, math;
}
