package com.spring.url.repository;

import com.spring.url.entity.Url;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UrlRepositoryImpl implements UrlRepository {

    private static Map<Long, Integer> indexMap = new HashMap<>();

    private static ArrayList<Url> urlList = new ArrayList<>();

    private static int sequence = 0;



    @Override
    public List<Url> findAll() {

        List<Url> resultList = new ArrayList<>();


        for(int index : indexMap.values()){
            resultList.add(urlList.get(index));
        }
        return resultList;

    }

    @Override
    public boolean save(long keyNum, Url url) {
        if(indexMap.containsKey(keyNum)){
            return false;
        }
        url.setUrlNum(sequence+1);
        indexMap.put(keyNum, sequence);
        urlList.add(url);
        sequence++;
        return true;
    }

    @Override
    public String findByKey(long keyNum) {
        int index = indexMap.get(keyNum);
        Url url = urlList.get(index);
        url.setCount(url.getCount()+1);
        return url.getLongUrl();
    }
}
