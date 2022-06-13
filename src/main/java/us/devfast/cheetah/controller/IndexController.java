package us.devfast.cheetah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/index","/home","/"})
    private String index(){
        return "index";
    }
}
