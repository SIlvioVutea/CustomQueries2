package com.example.demo.controllers;

import com.example.demo.models.Flight;
import com.example.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    public List<Flight> create(@RequestParam int totalFlights){
        return flightService.provisionNFlights(totalFlights);
    }

}
