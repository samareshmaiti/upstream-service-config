package com.stackroute.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityObject {
    private String objectType;
    //  private int id;
    private String content;
}
