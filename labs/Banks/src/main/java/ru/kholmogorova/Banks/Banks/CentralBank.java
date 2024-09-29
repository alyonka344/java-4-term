package ru.kholmogorova.Banks.Banks;

import ru.kholmogorova.Banks.Banks.Bank;
import ru.kholmogorova.Banks.Transaction.Transaction;

import java.util.ArrayList;

public class CentralBank {
    private final ArrayList<Bank> banks = new ArrayList<>();

    public void AddBank(Bank bank) {
        banks.add(bank);
    }

    public void NotifyBanks() {
        banks.forEach(Bank::PayPercents);
    }

    public void Execute(Transaction transaction) {
        transaction.Do();
    }

    public void Cancel(Transaction transaction) {
        transaction.Undo();
    }
}
