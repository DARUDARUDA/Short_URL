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

    private static char [] words = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public void encoding(String longUrl) {


        boolean pass = false;

        while (!pass){

            long keyNum = (long) (Math.random() * Math.pow(52, 6));
            StringBuilder sb = new StringBuilder();
            long encode = keyNum;
            for(int i = 0; i < 6; i++){
                sb.append(words[(int) (encode%52)]);
                encode /= 52;
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
            for (int j = 0; j < 52; j++){
                if(ch == words[j]){
                    check = true;
                    keyNum += j * pow;
                }
            }
            if(!check){
                return "/";
            }
            pow *=52;
        }


        return urlRepository.findByKey(keyNum);
    }

}
