package com.stackroute.DonorRegistration.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.DonorRegistration.Domain.Donor;
import com.stackroute.DonorRegistration.Exceptions.DonorAlreadyExistsException;
import com.stackroute.DonorRegistration.Exceptions.DonorNotFoundException;
import com.stackroute.DonorRegistration.Service.DonorService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DonorController  {

    private DonorService donorService;

    public static final String TOPIC = "Registration";

    @Autowired
    private KafkaTemplate<byte[],byte[]> kafkaTemplate;

    @Autowired
    public DonorController(DonorService donorService) {

        this.donorService = donorService;
    }

    /*registration of new Donor*/
    @PostMapping("donor")
    public ResponseEntity<?> saveNewDonor(@RequestBody Donor donor)  {



        ResponseEntity responseEntity = null;
        /*try{
            donorService.saveNewDonor(donor);
            responseEntity= new ResponseEntity<String>("Donor is registered", HttpStatus.CREATED);
        } catch (DonorAlreadyExistsException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }*/
        try {
            byte[] ba = new ObjectMapper().writeValueAsString(donor).getBytes();
            kafkaTemplate.send(new ProducerRecord<byte[],byte[]>(TOPIC, ba));
            responseEntity = new ResponseEntity("Successfully created", HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    /*Get all the Registered Donors*/
    @GetMapping("donors")
    public ResponseEntity<?> getAllDonors() {
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<List<Donor>>(donorService.getAllDonors(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    /* Deletion of  a Donor by id*/
    @DeleteMapping("/donor/{email}")
    public ResponseEntity<Donor> removeDonor(@PathVariable String email) {

        try {
            donorService.deleteById(email);
            return ResponseEntity.noContent().build();
        } catch (DonorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }

/*Updation of donor details*/
    @PutMapping("/donor/{email}")
    public ResponseEntity<Donor> updateDonorDetails(@RequestBody Donor donor) {

        ResponseEntity responseEntity;
        try{
            donorService.updateById(donor);
            responseEntity= new ResponseEntity<String>("Donor details updated!", HttpStatus.CREATED);
        } catch (DonorNotFoundException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/donors/{name}")
    public ResponseEntity<List<Donor>> getByName(@PathVariable String name) throws DonorNotFoundException {
        List<Donor> donor = donorService.getByName(name);
        return new ResponseEntity<List<Donor>>(donor, HttpStatus.OK);
    }

}
