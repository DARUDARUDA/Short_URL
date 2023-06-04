package com.spring.url.entity;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class Url {

    private long urlNum;
    private String key;
    private String longUrl;
    private long count;

}
