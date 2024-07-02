package com.example.demo.repositories;

import com.example.demo.models.Flight;
import com.example.demo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query ("select f from flight f where f.status = ?1 or f.status = ?2")
    List<Flight> findFlightsByStatusOrStatus(Status status, Status status2);
}
