package com.sfl.journeyapi.Controller;

import com.sfl.journeyapi.DTO.JourneyDTO;
import com.sfl.journeyapi.Entity.Journey;
import com.sfl.journeyapi.Service.JourneyServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JourneyController {

    @Autowired
    private JourneyServices journeyService;

    @GetMapping("/api/v1/journies")
    public ResponseEntity<List<Journey>> getAllJourneys() {
        return journeyService.getAllJourneys();
    }

    @GetMapping("api/v1/journies/{vehicleNumber}")
    public ResponseEntity<List<Journey>> getJourney(@PathVariable String vehicleNumber) {
        return journeyService.getJourneysByVehicleNumber(vehicleNumber);
    }

    @PostMapping("/api/v1/journies")
    public ResponseEntity<Journey> createJourney(@Valid @RequestBody JourneyDTO journeyDTO) {
        return journeyService.saveJourney(journeyDTO);
    }

}
