package com.sfl.tollapi.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TollRequest {
    @NotBlank(message = "vehicle number is mandatory")
    private String vehicleNumber;

    @NotBlank(message = "plaza name is mandatory")
    private String plaza;

    @Positive(message = "Amount should be greater than zero")
    private double amount;
}
