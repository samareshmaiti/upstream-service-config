package com.stackroute.demo.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Activities {
    private String actor;
    private String verb;
    private ActivityObject object;
}