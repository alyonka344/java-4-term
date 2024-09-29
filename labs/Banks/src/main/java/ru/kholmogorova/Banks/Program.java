package ru.kholmogorova.Banks;

import ru.kholmogorova.Banks.Banks.Bank;
import ru.kholmogorova.Banks.Banks.BankInfo;
import ru.kholmogorova.Banks.Banks.CentralBank;
import ru.kholmogorova.Banks.Clients.Client;
import ru.kholmogorova.Banks.TIme.MyDate;
import ru.kholmogorova.Banks.Transaction.Replenish;
import ru.kholmogorova.Banks.Transaction.Transaction;

import java.math.BigDecimal;

public class Program {
    public static void main(String[] args) {
        CentralBank centralBank = new CentralBank();
        var sberInfo = new BankInfo(10.4f, BigDecimal.valueOf(0.1));
        Bank bank = Bank.builder().name("PopaBank").bankInfo(sberInfo).build();
        Client alyona = Client.builder().id(1).clientName("popa").clientSurname("popova").build();

        bank.CreateAccountToUser(alyona, AccountType.Debit);

        Transaction transaction = new Replenish(1, alyona, BigDecimal.valueOf(100));

        centralBank.Execute(transaction);

        System.out.println(alyona.getAccounts().get(0).GetBalance());
    }
}
