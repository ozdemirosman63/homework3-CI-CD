package com.sau.hospitalapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("")
    public String getHome(){
        return "home";
    }

    @GetMapping("/last")
    public String getNewHome(){
        return "newHome";
    }

}
