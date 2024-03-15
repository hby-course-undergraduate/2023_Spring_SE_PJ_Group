package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface AccountDAO extends JpaRepository<Account, Integer> {
    @Query("select account from Account account where account.type = 'user' and account.state = true and account.userId = ?1 ")
    Account findUserByUserId(int userId);

    @Query("select account from Account account where account.type = 'user'  and account.userId = ?1 ")
    Account findAllUserByUserId(int userId);

    @Query("select account from Account account where account.type = 'shop' and account.state = true and account.userId = ?1 ")
    Account findShopByUserId(int userId);

    @Query("select account from Account account where account.type = 'shop' and  account.userId = ?1 ")
    Account findAllShopByUserId(int userId);

    @Query("select account from Account account where account.type = 'user' and account.state = true and account.accountId = ?1 ")
    Account findUserByAccountId(int accountId);

    @Query("select account from Account account where account.type = 'shop' and account.state = true and account.accountId = ?1 ")
    Account findShopByAccountId(int accountId);

    @Query("select account from Account account where account.type = 'profit' ")
    Account findProfitAccount();

    @Query("select account from Account account where account.type = 'middle' ")
    Account findMiddleAccount();

}
