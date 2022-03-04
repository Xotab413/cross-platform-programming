package com.example.cpp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YearController {

    private static final String YEAR = "year";
    private static final String RESULT = "result";

    @GetMapping(value = "/isleap")
    public String isLeap(
            Model model,
            @RequestParam(value = "year") String _year
    ) {
        try {
            Year year = new Year(Integer.parseInt(_year));
            model.addAttribute(YEAR, year.number);
            model.addAttribute(RESULT, year.isLeap);
        } catch (Throwable e) {
            return init(model);
        }
        return "year-page";

    }

    @GetMapping(value = "/")
    public String init(Model model) {
        model.addAttribute(YEAR, "");
        model.addAttribute(RESULT, "");
        return "year-page";
    }

}
