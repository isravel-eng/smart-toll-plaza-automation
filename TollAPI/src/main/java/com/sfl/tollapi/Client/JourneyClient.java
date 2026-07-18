package com.sfl.tollapi.Client;

import com.sfl.tollapi.DTO.JourneyRequest;
import com.sfl.tollapi.DTO.JourneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JourneyClient {

    @Autowired
    private RestTemplate restTemplate;

    public JourneyResponse saveJourney(JourneyRequest journeyRequest) {
        String url = "http://localhost:8083/api/v1/journies";
        HttpEntity<JourneyRequest> httpEntity = new HttpEntity<>(journeyRequest);
        ResponseEntity<JourneyResponse> response = restTemplate.postForEntity(url, httpEntity, JourneyResponse.class);
        return response.getBody();
    }
}
