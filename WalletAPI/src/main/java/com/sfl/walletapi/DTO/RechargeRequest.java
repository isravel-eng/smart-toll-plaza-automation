package com.sfl.walletapi.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeRequest {
    @NotBlank(message = "Fastag ID is required")
    private String fastagId;

    @Positive(message="Amount must be greater than 0")
    private double amount;
}
