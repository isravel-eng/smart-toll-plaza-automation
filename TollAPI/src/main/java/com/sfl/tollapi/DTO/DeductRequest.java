package com.sfl.tollapi.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeductRequest {
    private String fastagId;
    private double amount;
}
