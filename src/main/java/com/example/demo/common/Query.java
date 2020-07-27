package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/*条件查询*/
public class Query {
    private Integer startTime;
    private Integer endTime;
    private Integer classification;
    private String title;

}
