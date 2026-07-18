package com.sfl.vehicleapi.Entity;

import com.sfl.vehicleapi.Enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vehicleNumber;

    private String ownerName;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String fastagId;
}
