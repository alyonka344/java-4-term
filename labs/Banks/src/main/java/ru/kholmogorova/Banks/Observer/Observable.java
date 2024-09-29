package ru.kholmogorova.Banks.Observer;

import ru.kholmogorova.Banks.Banks.BankInfo;

public interface Observable<Type> {

    void Subscribe(Type observer);
    void Unsubscribe(Type observer);
    void NotifySubscribers(BankInfo info);
}
