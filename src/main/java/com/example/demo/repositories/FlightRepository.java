package com.example.demo.repositories;

import com.example.demo.models.Flight;
import com.example.demo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> getFlightsByStatusOrStatus(Status status, Status status2);
}
