package com.reabel.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoCApplicationController {

    @GetMapping("/table")
    public String table() {
        return "table";
    }

    @GetMapping("/record")
    public String greeting(@RequestParam(name = "name", required = true, defaultValue = "Not Found") String name,
            Model model) {
        model.addAttribute("name", name);
        return "record";
    }
}