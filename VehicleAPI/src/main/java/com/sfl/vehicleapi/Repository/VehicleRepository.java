package com.sfl.vehicleapi.Repository;

import com.sfl.vehicleapi.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    boolean existsByVehicleNumber(String vehicleNumber);
    boolean existsByFastagId(String fastagId);
    Vehicle findByVehicleNumber(String vehicleNumber);
}
