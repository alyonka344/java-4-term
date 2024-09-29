package ru.kholmogorova.Banks.Transaction;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.kholmogorova.Banks.Clients.Client;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Transfer implements Transaction {
    @NonNull
    private int accountId1;
    @NonNull
    private int accountId2;
    @NonNull
    private Client client1;
    @NonNull
    private Client client2;
    @NonNull
    private BigDecimal amount;
    private boolean result = false;
    @Override
    public void Do() {
        if (!result) {
            var account1 = client1.getAccounts().stream().filter(b -> b.GetId() == accountId1).findFirst().get();
            result = account1.WithdrawMoney(amount);
            if (result) {
                var account2 = client2.getAccounts().stream().filter(b -> b.GetId() == accountId2).findFirst().get();
                result = account2.ReplenishBalance(amount);
                if (!result) {
                    account1.ReplenishBalance(amount);
                }
            }
        }
    }

    @Override
    public void Undo() {
        if (result) {
            var account2 = client1.getAccounts().stream().filter(b -> b.GetId() == accountId1).findFirst().get();
            result = !account2.WithdrawMoney(amount);
            if (!result) {
                var account1 = client1.getAccounts().stream().filter(b -> b.GetId() == accountId1).findFirst().get();
                result = !account1.ReplenishBalance(amount);
                if (result) {
                    account2.ReplenishBalance(amount);
                }
            }
        }
    }
}
