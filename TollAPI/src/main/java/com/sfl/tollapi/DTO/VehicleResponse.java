package com.sfl.tollapi.DTO;

import com.sfl.tollapi.Enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponse {
    private int id;
    private String vehicleNumber;
    private String ownerName;
    private VehicleType vehicleType;
    private String fastagId;
}
