package ru.kholmogorova.Banks.Banks;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.kholmogorova.Banks.AccountType;
import ru.kholmogorova.Banks.Accounts.Accountable;
import ru.kholmogorova.Banks.Accounts.CreditAccount;
import ru.kholmogorova.Banks.Accounts.DebitAccount;
import ru.kholmogorova.Banks.Accounts.DepositAccount;
import ru.kholmogorova.Banks.Clients.Client;
import ru.kholmogorova.Banks.Observer.Observable;
import ru.kholmogorova.Banks.Observer.Observer;
import ru.kholmogorova.Banks.TIme.MyDate;
import ru.kholmogorova.Banks.TIme.TimeManager;

import java.math.BigDecimal;
import java.util.ArrayList;

@Builder
public class Bank implements Observable<Client> {
    private String name;
    private final ArrayList<Observer> subscribers = new ArrayList<>();
    private final ArrayList<Client> clients = new ArrayList<>();

    private MyDate lastUpdateDate;
    private BankInfo bankInfo;
    private int defaultTerm;
    private BigDecimal defaultLimit;

    @Override
    public void Subscribe(Client observer) {
        if (!subscribers.contains(observer)) {
            subscribers.add(observer);
        }
    }

    @Override
    public void Unsubscribe(Client observer) {
        subscribers.removeIf(client -> client == observer);
    }

    @Override
    public void NotifySubscribers(BankInfo info) {
        subscribers.forEach(b -> b.ReceiveInfo(info));
    }

    /**
     * force banks to pay interest
     */
    public void PayPercents() {
        for (Client client : clients) {
            for (Accountable account : client.getAccounts()) {
                long diff = TimeManager.GetCurrentDate().Subtract(lastUpdateDate);
                while (diff > 0) {
                    account.Update();
                    diff--;
                }
            }
        }
    }

    public void UpdateInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
        NotifySubscribers(bankInfo);
    }

    public void AddClient(Client client) {
        clients.add(client);
    }

    public void CreateAccountToUser(Client client, AccountType accountType) {
        if (accountType == AccountType.Deposit) {
            client.getAccounts().add(new DepositAccount(client.getAccounts().size() + 1, bankInfo.interest, defaultTerm));
        }
        if (accountType == AccountType.Debit) {
            client.getAccounts().add(new DebitAccount(client.getAccounts().size() + 1, bankInfo.interest));
        }
        if (accountType == AccountType.Credit) {
            client.getAccounts().add(new CreditAccount(client.getAccounts().size() + 1, defaultLimit, bankInfo.commission));
        }
    }


}
