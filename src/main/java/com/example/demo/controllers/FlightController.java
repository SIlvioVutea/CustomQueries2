package com.example.demo.controllers;

import com.example.demo.models.Flight;
import com.example.demo.models.Status;
import com.example.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    public List<Flight> create(@RequestParam (defaultValue = "100") int totalFlights){
        return flightService.provisionNFlights(totalFlights);
    }

    @GetMapping
    public Page<Flight> getPages (@RequestParam int page, @RequestParam int size){
        return flightService.getAll(page, size);
    }


    @GetMapping
    @RequestMapping("/ontime")
    public List<Flight> getAllOnTime (){
        return flightService.allFlightsOnTime();
    }
    @GetMapping
    @RequestMapping("/status")
    public List<Flight> getAllByStatuses(@RequestParam String status1, @RequestParam String status2){
        return flightService.allFlightsByStatus(status1, status2);
    }
}
