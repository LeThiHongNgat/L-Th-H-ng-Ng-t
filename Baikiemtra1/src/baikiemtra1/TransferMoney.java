/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baikiemtra1;

/**
 *
 * @author DELL
 */
import java.util.Random;

public class TransferMoney implements Runnable {
    private final Bank bank;
    private final int fromAcc;
    private final double maxAmount;
    private final int delay = 1000;

    public TransferMoney(Bank bank, int fromAcc, double maxAmount) {
        this.bank = bank;
        this.fromAcc = fromAcc;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Random rand = new Random();
                int toAcc = rand.nextInt(bank.size());
                double amount = rand.nextDouble() * maxAmount;
                bank.transfer(fromAcc, toAcc, amount);
                Thread.sleep (delay);
            }
        } catch (InterruptedException e) {
        }
    }
}