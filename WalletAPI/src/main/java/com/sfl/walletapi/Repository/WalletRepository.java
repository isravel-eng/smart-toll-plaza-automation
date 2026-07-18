package com.sfl.walletapi.Repository;

import com.sfl.walletapi.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByFastagId(String fastagId);
    boolean  existsByFastagId(String fastagId);
}
