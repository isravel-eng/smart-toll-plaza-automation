package com.sfl.tollapi.Client;

import com.sfl.tollapi.DTO.VehicleResponse;
import com.sfl.tollapi.Exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleClient {
    @Autowired
    private RestTemplate restTemplate;

    public VehicleResponse getVehicleByVehicleNumber(String vehicleNumber) {
        String url = "http://localhost:8081/api/v1/vehicles/" + vehicleNumber;
        try {
            ResponseEntity<VehicleResponse> response = restTemplate.getForEntity(url, VehicleResponse.class);
            return response.getBody();
        }
        catch(HttpClientErrorException.NotFound e){
            throw new VehicleNotFoundException("Vehicle not found in vehicle number "+vehicleNumber);
        }
    }
}
