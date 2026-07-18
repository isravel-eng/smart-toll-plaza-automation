package com.sfl.vehicleapi.Controller;

import com.sfl.vehicleapi.DTO.VehicleDTO;
import com.sfl.vehicleapi.Entity.Vehicle;
import com.sfl.vehicleapi.Service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/api/v1/vehicles")
    public ResponseEntity<Vehicle> postVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.postVehicle(vehicleDTO);
    }

    @GetMapping("/api/v1/vehicles/{vehicleNumber}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String vehicleNumber) {
        return vehicleService.getVehicleByVehicleNumber(vehicleNumber);
    }

    @GetMapping("/api/v1/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @PutMapping("api/v1/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int id,@Valid @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.updateVehicle(id,vehicleDTO);
    }

    @DeleteMapping("/api/v1/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        return  vehicleService.deleteById(id);
    }


}
