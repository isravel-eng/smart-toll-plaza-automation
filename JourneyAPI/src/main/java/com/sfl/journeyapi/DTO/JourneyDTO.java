package com.sfl.journeyapi.DTO;

import com.sfl.journeyapi.Enums.PaymentStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JourneyDTO {
    @NotBlank(message = "vehicle number is mandatory")
    private String vehicleNumber;

    @NotNull(message = "start time of journey is required")
    private LocalDateTime startTime;

    @NotNull(message = "end time of journey is required")
    private LocalDateTime endTime;

    @NotBlank(message = "Plaza name is required")
    private String plaza;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;
}
