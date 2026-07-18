package com.sfl.walletapi.Service;

import com.sfl.walletapi.DTO.DeductRequest;
import com.sfl.walletapi.DTO.RechargeRequest;
import com.sfl.walletapi.DTO.WalletRequest;
import com.sfl.walletapi.Entity.Wallet;
import com.sfl.walletapi.Exceptions.DuplicateFasTagException;
import com.sfl.walletapi.Exceptions.FasTagNotFoundException;
import com.sfl.walletapi.Exceptions.InsufficientBalanceException;
import com.sfl.walletapi.Exceptions.InvalidAmountException;
import com.sfl.walletapi.Repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@Slf4j
public class WalletService {

    @Autowired
    private WalletRepository  walletRepository;

    public ResponseEntity<Wallet> createWallet(WalletRequest walletRequest) {
        log.info("Received request to create a new wallet");
        if (walletRepository.existsByFastagId(walletRequest.getFastagId())) {
            log.warn("Duplicate fastag id {}", walletRequest.getFastagId());
            throw new DuplicateFasTagException("FasTag Id is must be Unique");
        }
        Wallet wallet = new Wallet();
        wallet.setFastagId(walletRequest.getFastagId());
        wallet.setBalance(0);
        wallet = walletRepository.save(wallet);
        log.info("Wallet created with id {}", wallet.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    public ResponseEntity<Wallet> recharge(RechargeRequest rechargeRequest) {
        log.info("Received request to recharge a wallet with fastag id {}",rechargeRequest.getFastagId());
        if(!walletRepository.existsByFastagId(rechargeRequest.getFastagId())) {
            log.warn("Fastag id {} not found to recharge", rechargeRequest.getFastagId());
            throw new FasTagNotFoundException("Requested FasTag Id is not Found");
        }
        if(rechargeRequest.getAmount() <= 100) {
            log.warn("Amount must be greater than 100");
            throw new InvalidAmountException("Amount must be greater than 100");
        }
        Wallet wallet = walletRepository.findByFastagId(rechargeRequest.getFastagId());
        wallet.setBalance(wallet.getBalance() + rechargeRequest.getAmount());
        wallet =  walletRepository.save(wallet);
        log.info("Wallet {} recharged Rs.{} ",wallet.getId(), rechargeRequest.getAmount());
        return  ResponseEntity.status(HttpStatus.OK).body(wallet);
    }

    public ResponseEntity<Wallet> deduct(DeductRequest deductRequest) {
        log.info("Received request to deduct a wallet with fastag id {}",deductRequest.getFastagId());
        if(!walletRepository.existsByFastagId(deductRequest.getFastagId())) {
            log.warn("Fastag id {} not found to deduct amount", deductRequest.getFastagId());
            throw new FasTagNotFoundException("Requested FasTag Id is not Found");
        }
        Wallet wallet = walletRepository.findByFastagId(deductRequest.getFastagId());
        if (wallet.getBalance() < deductRequest.getAmount()) {
            log.warn("Insufficient balance to deduct amount");
            throw new InsufficientBalanceException("Current Balance Rs."+wallet.getBalance()+" is lesser than required amount Rs."+deductRequest.getAmount());
        }
        wallet.setBalance(wallet.getBalance() - deductRequest.getAmount());
        wallet =  walletRepository.save(wallet);
        log.info("Wallet {} deducted Rs.{}", wallet.getId(), deductRequest.getAmount());
        return ResponseEntity.status(HttpStatus.OK).body(wallet);
    }

    public ResponseEntity<Wallet> getWalletByFastagId(String fastagId) {
        log.info("Received request to get wallet by fastag id {}", fastagId);
        if (!walletRepository.existsByFastagId(fastagId)) {
            log.warn("In requested FasTag Id, wallet is not Found");
            throw new FasTagNotFoundException("In requested FasTag Id, wallet is not Found");
        }
        log.info("Wallet found with fastag id {} ", fastagId);
        return ResponseEntity.status(HttpStatus.OK).body(walletRepository.findByFastagId(fastagId));
    }

    public ResponseEntity<List<Wallet>> getAllWallets() {
        log.info("Received request to get all wallets");
        List<Wallet> wallets = walletRepository.findAll();
        log.info("{} Wallets found", wallets.size());
        return ResponseEntity.status(HttpStatus.OK).body(wallets);
    }

    public ResponseEntity<String> deleteWalletById(int id) {
        log.info("Request received to Delete wallet with id : {}", id);
        Wallet wallet = walletRepository.findById(id).orElse(null);
        if (wallet == null) {
            log.warn("Wallet with id {} not found", id);
            throw new FasTagNotFoundException("Wallet with id " + id + " not found");
        }
        walletRepository.delete(wallet);
        log.info("Vehicle deleted successfuly with id : {}", id);
        return ResponseEntity.ok("Wallet with id "+id+" Successfully deleted");
    }

}
