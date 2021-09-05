package com.bitproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReportUIController {

    @RequestMapping(value = "/reportemployee", method = RequestMethod.GET)
    public ModelAndView reportemployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ui/reportemployee.html");
        return modelAndView;
    }
    @RequestMapping(value = "/samplereport", method = RequestMethod.GET)
    public ModelAndView samplereport() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ui/samplereport.html");
        return modelAndView;
    }

}
