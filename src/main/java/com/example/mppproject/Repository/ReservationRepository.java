package com.example.mppproject.Repository;

import com.example.mppproject.Model.Property;
import com.example.mppproject.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
