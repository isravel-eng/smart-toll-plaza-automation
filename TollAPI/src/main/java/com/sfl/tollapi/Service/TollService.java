package com.sfl.tollapi.Service;

import com.sfl.tollapi.Client.JourneyClient;
import com.sfl.tollapi.Client.VehicleClient;
import com.sfl.tollapi.Client.WalletClient;
import com.sfl.tollapi.DTO.*;
import com.sfl.tollapi.Exception.InsufficientBalanceException;
import com.sfl.tollapi.Exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sfl.tollapi.Enums.PaymentStatus.PAID;

@Service
public class TollService {

    @Autowired
    private VehicleClient vehicleClient;

    @Autowired
    private WalletClient walletClient;

    @Autowired
    private JourneyClient journeyClient;


    public ResponseEntity<TollResponse> payToll(TollRequest request){
        VehicleResponse vehicle = vehicleClient.getVehicleByVehicleNumber(request.getVehicleNumber());

        if(vehicle == null){
            throw new VehicleNotFoundException("Vehicle not found");
        }

        DeductRequest deductRequest = new DeductRequest();
        deductRequest.setAmount(request.getAmount());
        deductRequest.setFastagId(vehicle.getFastagId());

        WalletResponse walletResponse = walletClient.deductBalance(deductRequest);

        if(walletResponse==null){
            throw new InsufficientBalanceException("Insufficient wallet balance");
        }

        JourneyRequest journeyRequest = new JourneyRequest();
        journeyRequest.setVehicleNumber(vehicle.getVehicleNumber());
        journeyRequest.setPlaza(request.getPlaza());
        journeyRequest.setAmount(request.getAmount());
        journeyRequest.setPaymentStatus(PAID);
        journeyRequest.setStartTime(LocalDateTime.now());
        journeyRequest.setEndTime(LocalDateTime.now());

        JourneyResponse journeyResponse = journeyClient.saveJourney(journeyRequest);

        TollResponse tollResponse = new TollResponse();
        tollResponse.setVehicleNumber(journeyResponse.getVehicleNumber());
        tollResponse.setPlaza(journeyResponse.getPlaza());
        tollResponse.setAmount(journeyResponse.getAmount());
        tollResponse.setPaymentStatus(journeyRequest.getPaymentStatus());
        tollResponse.setFastagId(walletResponse.getFastagId());
        tollResponse.setMessage("Toll deducted successfully");
        return ResponseEntity.status(HttpStatus.OK.value()).body(tollResponse);
    }
}
