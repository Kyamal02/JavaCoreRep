package ru.itis.multithreading.ex3;

public class ATM {
    private final static Long maxBalance = 1_000_000L;
    private Long totalBalance;

    public ATM(Long totalBalance) {
        this.totalBalance = totalBalance;
    }

    private final Object monitor = new Object();

    boolean withdrawCash(Long amountOfMoney, Account account) {
        synchronized (monitor) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (amountOfMoney > account.getBalance()) {
                System.out.println("\n///\n" + account.getName() + " на балансе недостаточно средств " + "\n/// \n");
                return false;
            }

            if (totalBalance - amountOfMoney >= 0) {
                System.out.println("///\n"
                        + "Клиент: " + account.getName() + " ваш баланс: " + account.getBalance() + "\n"
                        + "Наличные сняты: " + amountOfMoney + "\n"
                        + "Ваш баланс: " + (account.getBalance() - amountOfMoney)
                        + "\n///\n");

                totalBalance -= amountOfMoney;
                account.setBalance(account.getBalance() - amountOfMoney);
                return true;
            }
            System.out.println(account.getName() + " в банкомате закончились средства");
            return false;
        }
    }

    public void topUp(Long amountOfMoney, Account account) {
        if (!account.isAdmin()) return;

        if (totalBalance + amountOfMoney > maxBalance) {
            System.out.println("Всю сумму положить не удастся. Баланс пополнен на: " + (totalBalance + amountOfMoney - maxBalance) + "руб");
        }

    }

    public Long getTotalBalance() {
        return totalBalance;
    }
}
