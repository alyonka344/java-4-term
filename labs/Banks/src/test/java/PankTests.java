import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.kholmogorova.Banks.AccountType;
import ru.kholmogorova.Banks.Banks.Bank;
import ru.kholmogorova.Banks.Banks.BankInfo;
import ru.kholmogorova.Banks.Banks.CentralBank;
import ru.kholmogorova.Banks.Clients.Client;
import ru.kholmogorova.Banks.Transaction.Replenish;
import ru.kholmogorova.Banks.Transaction.Transaction;
import ru.kholmogorova.Banks.Transaction.Transfer;
import ru.kholmogorova.Banks.Transaction.Withdraw;

import java.math.BigDecimal;

public class PankTests {
    private CentralBank centralBank;
    Bank bank;
    Client alyona;
    Client roma;

    public PankTests() {
        centralBank = new CentralBank();
        var sberInfo = new BankInfo(10.4f, BigDecimal.valueOf(0.1));
        bank = Bank.builder().name("PopaBank").bankInfo(sberInfo).build();
        alyona = Client.builder().id(1).clientName("alyona").clientSurname("Holms").build();
        roma = Client.builder().id(2).clientName("popa").clientSurname("popov").build();
        bank.AddClient(alyona);
        bank.AddClient(roma);
        bank.CreateAccountToUser(alyona, AccountType.Debit);
        bank.CreateAccountToUser(roma, AccountType.Debit);
        centralBank.AddBank(bank);
    }
    @Test
    public void  ReplenishSuccessful() {
        // Arrange
        Transaction transaction = new Replenish(1, alyona, BigDecimal.valueOf(100));
        var balance1 = alyona.getAccounts().get(0).GetBalance();

        // Act
        centralBank.Execute(transaction);
        var balance2 = alyona.getAccounts().get(0).GetBalance();

        // Assert
        assertEquals(balance2, balance1.add(BigDecimal.valueOf(100)));
    }
    @Test
    public void  WithdrawSuccessful() {
        // Arrange
        Transaction replenish = new Replenish(1, alyona, BigDecimal.valueOf(100));
        Transaction withdraw = new Withdraw(1, alyona, BigDecimal.valueOf(20));
        var balance1 = alyona.getAccounts().get(0).GetBalance();

        // Act
        centralBank.Execute(replenish);
        centralBank.Execute(withdraw);
        var balance2 = alyona.getAccounts().get(0).GetBalance();

        // Assert
        assertEquals(balance2, balance1.add(BigDecimal.valueOf(80)));
    }
    @Test
    public void  WithdrawFailed() {
        // Arrange
        Transaction replenish = new Replenish(1, alyona, BigDecimal.valueOf(100));
        Transaction withdraw = new Withdraw(1, alyona, BigDecimal.valueOf(2000));
        var balance1 = alyona.getAccounts().get(0).GetBalance();

        // Act
        centralBank.Execute(replenish);
        centralBank.Execute(withdraw);
        var balance2 = alyona.getAccounts().get(0).GetBalance();

        // Assert
        assertEquals(balance2, balance1.add(BigDecimal.valueOf(100)));
    }
    @Test
    public void  TransferSuccess() {
        // Arrange
        Transaction replenish = new Replenish(1, alyona, BigDecimal.valueOf(100));
        Transaction transfer = new Transfer(1, 1, alyona, roma, BigDecimal.valueOf(50));
        var balance11 = alyona.getAccounts().get(0).GetBalance();
        var balance21 = roma.getAccounts().get(0).GetBalance();

        // Act
        centralBank.Execute(replenish);
        centralBank.Execute(transfer);
        var balance12 = alyona.getAccounts().get(0).GetBalance();
        var balance22 = roma.getAccounts().get(0).GetBalance();

        // Assert
        assertEquals(balance12, balance11.add(BigDecimal.valueOf(50)));
        assertEquals(balance22, balance21.add(BigDecimal.valueOf(50)));

    }

    @Test
    public void  TransferFailed() {
        // Arrange
        Transaction replenish = new Replenish(1, alyona, BigDecimal.valueOf(100));
        Transaction transfer = new Transfer(1, 1, roma, alyona, BigDecimal.valueOf(50));
        var balance11 = alyona.getAccounts().get(0).GetBalance();
        var balance21 = roma.getAccounts().get(0).GetBalance();

        // Act
        centralBank.Execute(replenish);
        centralBank.Execute(transfer);
        var balance12 = alyona.getAccounts().get(0).GetBalance();
        var balance22 = roma.getAccounts().get(0).GetBalance();

        // Assert
        assertEquals(balance12, balance11.add(BigDecimal.valueOf(100)));
        assertEquals(balance22, balance21.add(BigDecimal.valueOf(0)));

    }
}
