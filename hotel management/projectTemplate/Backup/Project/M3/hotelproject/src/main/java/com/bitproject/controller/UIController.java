package com.bitproject.controller;

import com.bitproject.model.User;
import com.bitproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UIController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView error(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.html");
        return modelAndView;
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ModelAndView config(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("config.html");
        return modelAndView;
    }

    @GetMapping(value = {"/employee" })
    public ModelAndView employeeui() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user!= null){
            modelAndView.setViewName("employee.html");
        }
        else
            modelAndView.setViewName("error.html");

        return modelAndView;
    }

    @GetMapping(path = "/employee/{id}")
    public ModelAndView employeessui() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user!= null){
            modelAndView.setViewName("employee.html");
        }
        else
            modelAndView.setViewName("error.html");

        return modelAndView;
    }

    @GetMapping(value = "/privilage")
    public ModelAndView privilageui() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user!= null){
            modelAndView.setViewName("privilage.html");
        }
        else
            modelAndView.setViewName("error.html");

        return modelAndView;
    }




    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user!= null){
            modelAndView.setViewName("user.html");
        }
        else
            modelAndView.setViewName("error.html");

        return modelAndView;
    }







}





