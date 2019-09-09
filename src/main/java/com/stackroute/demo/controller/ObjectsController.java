package com.stackroute.demo.controller;

import com.stackroute.demo.domain.Objects;
import com.stackroute.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectsController {
    @Autowired
    DataService dataService;
    @GetMapping("/hey")
    public ResponseEntity<?> saveTrack()  {

        dataService.send();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
