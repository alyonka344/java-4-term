package ru.kholmogorova.Banks.Accounts;

import java.math.BigDecimal;

/**
 * Account interface for Clients
 */
public interface Accountable {
    BigDecimal GetBalance();

    /**
     * Replenish Balance for user with this account
     */
    boolean ReplenishBalance(BigDecimal money);

    /**
     * Withdraw money for user with this account
     */
    boolean WithdrawMoney(BigDecimal money);
    void Update();
    int GetId();
}
