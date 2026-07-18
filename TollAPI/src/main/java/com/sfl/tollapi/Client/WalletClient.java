package com.sfl.tollapi.Client;

import com.sfl.tollapi.DTO.DeductRequest;
import com.sfl.tollapi.DTO.WalletResponse;
import com.sfl.tollapi.Exception.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class WalletClient {
    @Autowired
    private RestTemplate restTemplate;

    public WalletResponse deductBalance(DeductRequest deductRequest) {
        String url = "http://localhost:8082/api/v1/wallets/deduct";
        HttpEntity<DeductRequest> request = new HttpEntity<>(deductRequest);
        try {
            ResponseEntity<WalletResponse> response = restTemplate.exchange(url, HttpMethod.PUT, request, WalletResponse.class);
            return response.getBody();
        }
        catch (HttpClientErrorException.BadRequest e) {
            throw new InsufficientBalanceException("Insufficient balance for deduction of toll pay");
        }
    }

    public WalletResponse getWalletByFastagId(String fastagId) {
        String url = "http://localhost:8082/api/v1/wallets/"+fastagId;
        ResponseEntity<WalletResponse> response = restTemplate.getForEntity(url, WalletResponse.class);
        return response.getBody();
    }
}
