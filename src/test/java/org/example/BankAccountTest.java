package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BankAccountTest {
    private BankAccount account;
    @BeforeEach
    public void setUp() {
        account = new BankAccount(100.0);
    }
    @Test
    @DisplayName("Коректне поповнення рахунку")
    public void testSuccessfulDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), "Баланс має збільшитися на суму поповнення");
    }
    @Test
    @DisplayName("Коректне зняття коштів")
    public void testSuccessfulWithdraw() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), "Баланс має зменшитися на 40");
    }
    @Test
    @DisplayName("Зняття всієї суми (межовий випадок)")
    public void testWithdrawExactBalance() {
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance(), "Баланс має бути 0");
    }
    @Test
    public void testZeroDeposit() {
        account.deposit(0.0);
        assertEquals(100.0, account.getBalance(), "Баланс не повинен змінитися");
    }
    @Test
    @DisplayName("Виняток при знятті суми, що перевищує баланс")
    public void testWithdrawMoreThanBalanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150.0),
                "Очікується виняток при спробі зняти більше коштів, ніж є на балансі");
    }
    @Test
    public void testNegativeDepositThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-20.0),
                "Очікується виняток при від'ємному поповненні");
    }
    @Test
    @DisplayName("Перевірка комплексної взаємодії з рахунком")
    public void testMultipleConditionsWithAssertAll() {
        BankAccount newAccount = new BankAccount(500.0);

        assertAll("Комплексна перевірка операцій",
                () -> {
                    newAccount.deposit(200.0);
                    assertEquals(700.0, newAccount.getBalance());
                },
                () -> {
                    newAccount.withdraw(600.0);
                    assertEquals(100.0, newAccount.getBalance());
                },
                () -> assertNotNull(newAccount, "Об'єкт не має бути null")
        );
    }
}
