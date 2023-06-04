package com.spring.url.service;

import com.spring.url.entity.Url;

import java.util.List;

public interface UrlService {

    // 전체 조회
    List<Url> findAll();

    // 인코딩
    void encoding(String longUrl);

    // 디코딩
    String decoding(String key);

}
