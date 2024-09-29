package ru.kholmogorova.Banks.Observer;

import ru.kholmogorova.Banks.Banks.BankInfo;

public interface Observer {
    void ReceiveInfo(BankInfo info);
}
