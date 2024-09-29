package ru.kholmogorova.Banks.Transaction;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.kholmogorova.Banks.Clients.Client;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Withdraw implements Transaction {
    @NonNull
    private int accountId;
    @NonNull
    private Client client;
    @NonNull
    private BigDecimal amount;
    private boolean result = false;
    @Override
    public void Do() {
        if (!result) {
            var account = client.getAccounts().stream().filter(b -> b.GetId() == accountId).findFirst().get();
            result = account.WithdrawMoney(amount);
        }
    }

    @Override
    public void Undo() {
        if (result) {
            var account = client.getAccounts().stream().filter(b -> b.GetId() == accountId).findFirst().get();
            result = !account.ReplenishBalance(amount);
        }
    }
}
