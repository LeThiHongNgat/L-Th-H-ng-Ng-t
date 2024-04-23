/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baikiemtra1;

/**
 *
 * @author DELL
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

    public Bank(int n, double initBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initBalance;
        }
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public int size() {
        return accounts.length;
    }

    public synchronized double getTotalBalance() {
        double total = 0;
        for (double a : accounts) {
            total += a;
        }
        return total;
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }

            System.out.println(Thread.currentThread().getName());

            accounts[from] -= amount;
            System.out.printf("Transfer %.2f from account %d to account %d%n", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total balance: %.2f%n", getTotalBalance());

            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }
}
