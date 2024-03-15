package com.group4.eshopping.dao;

import com.group4.eshopping.entity.TransferAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferAccountDAO extends JpaRepository<TransferAccount, Integer> {
    List<TransferAccount> findByToIdAndToTypeAndTypeAndCreateAtAfter(int toId, String toType, String type, LocalDateTime createAt);

    List<TransferAccount> findByToIdAndToTypeAndCreateAtAfter(int toId, String toType, LocalDateTime createAt);

    List<TransferAccount> findByFromIdAndFromTypeAndCreateAtAfter(int fromId, String fromType, LocalDateTime createAt);

    List<TransferAccount> findByToTypeAndCreateAtAfter(String toType, LocalDateTime createAt);

    List<TransferAccount> findByFromTypeAndCreateAtAfter(String fromType, LocalDateTime createAt);
}
