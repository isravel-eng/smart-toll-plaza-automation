package com.sfl.journeyapi.Service;

import com.sfl.journeyapi.DTO.JourneyDTO;
import com.sfl.journeyapi.Entity.Journey;
import com.sfl.journeyapi.Exceptions.JourneryNotFoundException;
import com.sfl.journeyapi.Repository.JourneyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JourneyServices {

    @Autowired
    private JourneyRepository journeyRepository;

    public ResponseEntity<Journey> saveJourney(JourneyDTO journeyDTO) {
        log.info("Request to save Journey for vehicle number : {}", journeyDTO.getVehicleNumber());
        Journey journey = new Journey();
        journey.setVehicleNumber(journeyDTO.getVehicleNumber());
        journey.setStartTime(journeyDTO.getStartTime());
        journey.setEndTime(journeyDTO.getEndTime());
        journey.setAmount(journeyDTO.getAmount());
        journey.setPlaza(journeyDTO.getPlaza());
        journey.setPaymentStatus(journeyDTO.getPaymentStatus());
        journey = journeyRepository.save(journey);
        log.info("Journey is successfully saved with id: {}", journey.getId());
        return  ResponseEntity.status(HttpStatus.CREATED).body(journey);
    }

    public ResponseEntity<List<Journey>> getAllJourneys() {
        log.info("Request to fetch all Journeys");
        List<Journey> journeys = journeyRepository.findAll();
        if (journeys.isEmpty()) {
            throw new JourneryNotFoundException("There are no Journeys in the database");
        }
        log.info("fetched journeys size is {}", journeys.size());
        return  ResponseEntity.status(HttpStatus.OK).body(journeys);
    }

    public ResponseEntity<List<Journey>> getJourneysByVehicleNumber(String VehicleNumber) {
        log.info("Request to fetch journeys by vehicle number : {}", VehicleNumber);
        List<Journey> journeys = journeyRepository.findByVehicleNumber(VehicleNumber);
        if(journeys.isEmpty()) {
            log.info("No journeys found for vehicle number " + VehicleNumber);
            throw new JourneryNotFoundException("No journeys found for vehicle number " + VehicleNumber);
        }
        log.info("fetched vehicle journeys size is {}", journeys.size());
        return  ResponseEntity.status(HttpStatus.OK).body(journeys);
    }
}
