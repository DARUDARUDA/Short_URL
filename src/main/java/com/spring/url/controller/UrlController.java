package com.spring.url.controller;

import com.spring.url.entity.Url;
import com.spring.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }



    @RequestMapping("/")
    public String home(Model model){

        List<Url> urlList = urlService.findAll();
        if (urlList != null){
            model.addAttribute("urlList", urlList);
        }

        return "home";
    }

    @RequestMapping("/{address}")
    public String redirect(@PathVariable String address){;
        return "redirect:" + urlService.decoding(address);
    }

    @RequestMapping(value = "url/register", method = RequestMethod.POST)
    public String register(String longUrl){

        urlService.encoding(longUrl);

        return "redirect:/";
    }


}
