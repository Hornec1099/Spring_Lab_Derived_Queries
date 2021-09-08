package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping (value = "/whiskys")
    public ResponseEntity<List<Whisky>> getAllWhiskiesByCertainCriteria(
            @RequestParam(name = "year", required =false) Integer year,
            @RequestParam(name = "distilleryId", required = false) Long distilleryId,
            @RequestParam(name = "age", required = false) Integer age){
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }else if (distilleryId != null && age != null){
            Optional<Distillery> distillery = distilleryRepository.findById(distilleryId);
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryAndAge(distillery, age), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }


}
