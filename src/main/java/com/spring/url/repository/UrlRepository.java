package com.spring.url.repository;

import com.spring.url.entity.Url;

import java.util.List;

public interface UrlRepository {

    // 전체조회
    List<Url> findAll();

    // 단축 url 저장(이미 존재하는 키값이면 저장하지 않고 false를 반환)
    boolean save(long keyNum, Url url);

    // 키값 이용 조회
    String findByKey(long keyNum);






}
