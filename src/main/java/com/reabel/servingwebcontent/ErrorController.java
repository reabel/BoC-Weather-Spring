package com.reabel.servingwebcontent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
