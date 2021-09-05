package com.bitproject.controller;


import com.bitproject.model.Material;
import com.bitproject.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/material")
public class MaterialController {

 @Autowired
private MaterialRepository dao;


@GetMapping(value = "/findAll",produces = "application/json")
   public List <Material> findAll(){
    return dao.findAll();


}
}
