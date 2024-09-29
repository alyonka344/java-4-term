package ru.kholmogorova.Banks.Clients;

import lombok.Builder;
import lombok.Getter;
import ru.kholmogorova.Banks.Accounts.Accountable;
import ru.kholmogorova.Banks.Banks.BankInfo;
import ru.kholmogorova.Banks.Observer.Observer;

import java.util.ArrayList;

@Builder
public class Client implements Observer {
    private long id;
    private String clientName;
    private String clientSurname;
    private String address;
    private String passport;
    @Getter
    private final ArrayList<Accountable> accounts =  new ArrayList<>();
    private final ArrayList<String> messages =  new ArrayList<>();

    @Override
    public void ReceiveInfo(BankInfo info) {
        messages.add(info.toString());
    }
}
