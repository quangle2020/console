package com.quanglv.repository.second;

import com.quanglv.domain.second.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, String> {
}
