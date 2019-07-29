package com.accp.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewAction {

    @GetMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }

}
