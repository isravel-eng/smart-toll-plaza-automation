package com.sfl.walletapi.Controller;


import com.sfl.walletapi.DTO.DeductRequest;
import com.sfl.walletapi.DTO.RechargeRequest;
import com.sfl.walletapi.DTO.WalletRequest;
import com.sfl.walletapi.Entity.Wallet;
import com.sfl.walletapi.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/api/v1/wallets")
    public ResponseEntity<List<Wallet>> getWallets() {
        return walletService.getAllWallets();
    }

    @GetMapping("/api/v1/wallets/{fastagId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable("fastagId") String fastagId) {
        return walletService.getWalletByFastagId(fastagId);
    }

    @PostMapping("/api/v1/wallets")
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody WalletRequest walletRequest) {
        return  walletService.createWallet(walletRequest);
    }

    @PutMapping("/api/v1/wallets/recharge")
    public ResponseEntity<Wallet> recharge(@Valid @RequestBody RechargeRequest rechargeRequest) {
        return  walletService.recharge(rechargeRequest);
    }

    @PutMapping("/api/v1/wallets/deduct")
    public ResponseEntity<Wallet> deduct(@Valid @RequestBody DeductRequest deductRequest) {
        return  walletService.deduct(deductRequest);
    }

    @DeleteMapping("/api/v1/wallets/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable("id") int id) {
        return  walletService.deleteWalletById(id);
    }
}
