package ru.kholmogorova.Banks.Accounts;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@RequiredArgsConstructor
public class CreditAccount implements Accountable {
    @NonNull
    private int id;
    private BigDecimal balance = BigDecimal.valueOf(0);
    @NonNull
    private BigDecimal limit;
    @NonNull
    private float commission;

    @Override
    public BigDecimal GetBalance() {
        return balance;
    }

    @Override
    public boolean ReplenishBalance(BigDecimal money) {
        balance = balance.add(money);
        return true;
    }

    @Override
    public boolean WithdrawMoney(BigDecimal money) {
        if (balance.compareTo(limit.negate()) > 0) {
            balance = balance.subtract(money);

            if (balance.compareTo(BigDecimal.valueOf(0)) < 0) {
                balance = balance.subtract(balance.multiply(BigDecimal.valueOf(commission)));
            }

            return true;
        }

        return false;
    }

    @Override
    public void Update() {

    }

    @Override
    public int GetId() {
        return id;
    }
}
