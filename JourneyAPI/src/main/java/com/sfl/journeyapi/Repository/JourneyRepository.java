package com.sfl.journeyapi.Repository;

import com.sfl.journeyapi.Entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
    List<Journey> findByVehicleNumber(String vehicleNumber);
}
