package com.example.demo.services;

import com.example.demo.models.Flight;
import com.example.demo.models.Status;
import com.example.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class FlightService {
    @Autowired
    private FlightRepository repository;

    public String generateString(int length){
        Random random = new Random();
        return  random.ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public Status generateRandomStatus(){
        Random random = new Random();
        Status result = Status.ONTIME;
        int status = random.nextInt(1,3);
        switch (status){
            case 1:
                result = Status.ONTIME;
            case 2:
                result = Status.DELAYED;
            case 3:
                result = Status.CANCELLED;
        }

        return result;
    }

    public int checkIfNIsEmpty(Integer n){
        if (n.describeConstable().isEmpty()){
            n=100;
            return n;
        }
        return n;
    }

    public List<Flight> provisionNFlights(int n){
        int maxRange = checkIfNIsEmpty(n) + 1;
        List<Flight> flights = IntStream.range(1,maxRange)
                .mapToObj(flight-> new Flight(
                        generateString(10),
                        generateString(10),
                        generateString(10),
                        generateRandomStatus()
                ))
                .toList();
        return repository.saveAll(flights);
    }

    public Page<Flight> getAll(int pageNumber, int size){
        Sort sort = Sort.by(Sort.Direction.ASC, "fromAirports");
        Pageable page = PageRequest.of(pageNumber, size, sort);
        return repository.findAll(page);
    }

    public List<Flight> allFlightsOnTime (){
        List<Flight> allFlights = repository.findAll();
        List<Flight> result = new ArrayList<>();
        for(Flight flight : allFlights){
            if(flight.getStatus().equals(Status.ONTIME)){
                result.add(flight);
            }
        }
        return result;
    }
    public List<Flight> allFlightsByStatus (Status p1, Status p2){
        return repository.getFlightsByStatusOrStatus(p1, p2);
    }
}
