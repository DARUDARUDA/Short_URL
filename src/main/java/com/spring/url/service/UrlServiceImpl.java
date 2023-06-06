package com.spring.url.service;

import com.spring.url.entity.Url;
import com.spring.url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    UrlRepository urlRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }


    @Override
    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    private static char [] words = {'2', '3', '4', '5', '6', '7', '8', '9' ,'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public void encoding(String longUrl) {


        boolean pass = false;

        while (!pass){

            long keyNum = (long) (Math.random() * Math.pow(52, 6));
            StringBuilder sb = new StringBuilder();
            long encode = keyNum;
            for(int i = 0; i < 6; i++){
                sb.append(words[(int) (encode%56)]);
                encode /= 56;
            }

            Url url = Url.builder().key(String.valueOf(sb)).longUrl(longUrl).build();

            System.out.println(keyNum);

            pass = urlRepository.save(keyNum, url);
        }

    }


    @Override
    public String decoding(String key) {

        if(key.length() != 6){
            return "/";
        }

        long pow = 1;
        long keyNum = 0;

        for (int i = 0; i < 6; i++){
            char ch = key.charAt(i);
            boolean check = false;
            for (int j = 0; j < 56; j++){
                if(ch == words[j]){
                    check = true;
                    keyNum += j * pow;
                }
            }
            if(!check){
                return "/";
            }
            pow *=56;
        }


        return urlRepository.findByKey(keyNum);
    }

}
