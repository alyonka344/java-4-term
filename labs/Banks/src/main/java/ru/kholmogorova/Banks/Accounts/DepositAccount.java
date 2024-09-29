package ru.kholmogorova.Banks.Accounts;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.kholmogorova.Banks.TIme.MyDate;
import ru.kholmogorova.Banks.TIme.TimeManager;

import java.math.BigDecimal;

@ToString
@RequiredArgsConstructor
public class DepositAccount implements Accountable {
    @NonNull
    private int id;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private final MyDate creationDate = TimeManager.GetCurrentDate();
    @NonNull
    private BigDecimal balanceInterest;
    @NonNull
    private long term;

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
        if ((creationDate.Subtract(TimeManager.GetCurrentDate())) < term) {
            return false;
        }

        if (balance.subtract(money).compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.subtract(money);
            return true;
        }

        return false;
    }

    @Override
    public void Update() {
        balance = balance.add(balance.multiply(balanceInterest));
    }

    @Override
    public int GetId() {
        return id;
    }
}
