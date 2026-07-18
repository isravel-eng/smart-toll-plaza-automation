package com.sfl.vehicleapi.Service;

import com.sfl.vehicleapi.DTO.VehicleDTO;
import com.sfl.vehicleapi.Entity.Vehicle;
import com.sfl.vehicleapi.Exception.DuplicateVehicleException;
import com.sfl.vehicleapi.Exception.VehicleNotFoundException;
import com.sfl.vehicleapi.Repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

   public ResponseEntity<Vehicle>  postVehicle(VehicleDTO vehicleDTO) {
       log.info("Register request received for vehicle : {}", vehicleDTO.getVehicleType());

        if (vehicleRepository.existsByVehicleNumber(vehicleDTO.getVehicleNumber())) {
            log.warn("Duplicate vehicle number : {}", vehicleDTO.getVehicleNumber());
            throw new DuplicateVehicleException("In this vehicle number , already vehicle exists");
        }

        if (vehicleRepository.existsByFastagId(vehicleDTO.getFastagId())) {
            log.warn("Duplicate FASTag id : {}", vehicleDTO.getFastagId());
            throw new DuplicateVehicleException("In this Fastag id , already vehicle exists");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setFastagId(vehicleDTO.getFastagId());
        vehicle.setOwnerName(vehicleDTO.getOwnerName());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle registered successfuly with id : {}", vehicle.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
   }

   public ResponseEntity<List<Vehicle>>  getAllVehicles() {
       log.info("Fetching all vehicles");
       List<Vehicle> vehicles = vehicleRepository.findAll();
       log.info("Total vehicles : {}", vehicles.size());
       return ResponseEntity.ok(vehicles);
   }

   public ResponseEntity<Vehicle> getVehicleByVehicleNumber(String vehicleNumber) {
       log.info("Fetching vehicle by vehicle number : {}", vehicleNumber);
       Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
       if (vehicle == null) {
           log.warn("Vehicle not found with vehicle number : {}", vehicleNumber);
           throw new VehicleNotFoundException("Vehicle not found with vehicle number : " + vehicleNumber);
       }
       log.info("Vehicle found successfuly with id : {}", vehicle.getId());
       return ResponseEntity.status(HttpStatus.OK).body(vehicle);
   }

   public ResponseEntity<Vehicle> updateVehicle(int id,VehicleDTO vehicleDTO) {
       if (vehicleRepository.existsByVehicleNumber(vehicleDTO.getVehicleNumber())) {
           throw new DuplicateVehicleException("In this vehicle number , already vehicle exists");
       }
       if (vehicleRepository.existsByFastagId(vehicleDTO.getFastagId())) {
           throw new DuplicateVehicleException("In this Fastag id , already vehicle exists");
       }
       log.info("Updating vehicle with id : {}", id);
       Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
       if (vehicle == null) {
           log.warn("Vehicle with id {} not found to update", id);
           throw new VehicleNotFoundException("Vehicle not found at given Id");
       }
       vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
       vehicle.setFastagId(vehicleDTO.getFastagId());
       vehicle.setOwnerName(vehicleDTO.getOwnerName());
       vehicle.setVehicleType(vehicleDTO.getVehicleType());
       log.info("Vehicle updated successfuly with id : {}", vehicle.getId());
       return ResponseEntity.ok(vehicleRepository.save(vehicle));
   }

   public ResponseEntity<String> deleteById(int id) {
       log.info("Deleting vehicle with id : {}", id);
       Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
       if (vehicle == null) {
           log.warn("Vehicle with id {} not found to delete", id);
           throw new VehicleNotFoundException("Vehicle not found at given Id");
       }
       vehicleRepository.deleteById(id);
       log.info("Vehicle deleted successfuly with id : {}", id);
       return ResponseEntity.ok("Vehicle Successfully deleted");
   }
}
