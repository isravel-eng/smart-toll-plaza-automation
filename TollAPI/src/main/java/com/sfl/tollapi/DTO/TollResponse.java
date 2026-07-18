package com.sfl.tollapi.DTO;

import com.sfl.tollapi.Enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TollResponse {
    private String vehicleNumber;
    private String fastagId;
    private String plaza;
    private double amount;
    private String message;
    private PaymentStatus paymentStatus;
}
