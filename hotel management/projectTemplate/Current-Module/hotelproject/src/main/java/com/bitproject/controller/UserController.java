package com.bitproject.controller;

import com.bitproject.model.Role;
import com.bitproject.model.User;
import com.bitproject.model.UserRole;
import com.bitproject.repository.UserRepository;
import com.bitproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping(value = "/user")
@RestController
public class UserController {


    @Autowired
    private UserRepository dao;

    @Autowired
    private PrevilageController PrevilageController;


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/logeduser", method = RequestMethod.GET)
    public User getLogedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        return user;
    }

    @GetMapping(value = "/getAdmin", produces = "application/json")
    public User getAdmin() {
        return dao.getAdmin();
    }

    @GetMapping(path = "/getuser/{userName}", produces = "application/json")
    public User getUserName(@PathVariable("userName")String userName) {

        return dao.findByLoggedName(userName);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public List<User> user() {
        return dao.list();
    }


    @GetMapping(value = "/findAll", params = {"page", "size"}, produces = "application/json")
    public Page<User> getAll(@RequestParam("page") int page, @RequestParam("size") int size ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        HashMap<String,Boolean> priv = PrevilageController.getPrivilages(user,"USER");
        if(user!= null && priv.get("select")){
            return dao.findAll(PageRequest.of(page, size, Sort.Direction.DESC,"id"));
        }
        return null;

    }


    @GetMapping(value = "/findAll",params = {"page", "size","searchtext"}, produces = "application/json")
    public Page<User> findAll(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("searchtext") String searchtext) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        HashMap<String,Boolean> priv = PrevilageController.getPrivilages(user,"USER");
        if(user!= null && priv.get("select")){
            return dao.findAll(searchtext,PageRequest.of(page, size, Sort.Direction.DESC,"id"));
        }
        return null;
    }


    @Transactional
    @PostMapping()
    public String add(@Validated @RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User exuser = userService.findUserByUserName(auth.getName());
        HashMap<String,Boolean> priv = PrevilageController.getPrivilages(exuser,"USER");
        if(exuser!= null && priv.get("add")){
            try {
                System.out.println("aaaaaaaaaaaaaaaaaa");
                userService.saveUser(user);
                return "0";
            } catch (Exception e) {
                return "Error-Saving : " + e.getMessage();
            }

        } else {
            return "Error-Saving : You have no Permission";
        }

    }



    @PutMapping
    public String update(@Validated @RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User exuser = userService.findUserByUserName(auth.getName());
        HashMap<String,Boolean> priv = PrevilageController.getPrivilages(exuser,"USER");
        if(exuser!= null && priv.get("update")){
            try {
            dao.save(user);
                return "0";
            }
            catch(Exception e) {
                return "Error-Saving : " + e.getMessage();
            }
        }
        else
                    return "Error-Updating : You have no Permission";
    }


    @DeleteMapping
    public String delete(@RequestBody User user ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User exuser = userService.findUserByUserName(auth.getName());
        HashMap<String,Boolean> priv = PrevilageController.getPrivilages(exuser,"USER");
        if(exuser!= null && priv.get("delete")){
            try {
                user.setActive(false);
                 dao.save(user);
                    return "0";

            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }

        else
             return "Error-Deleting : You have no Permission";

    }



}