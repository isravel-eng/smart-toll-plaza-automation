package com.sfl.tollapi.Controller;

import com.sfl.tollapi.DTO.TollRequest;
import com.sfl.tollapi.DTO.TollResponse;
import com.sfl.tollapi.Service.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TollController {

    @Autowired
    private TollService tollService;

    @PostMapping("/api/v1/tolls")
    public ResponseEntity<TollResponse> payTol(@RequestBody TollRequest tollRequest){
        return tollService.payToll(tollRequest);
    }
}
