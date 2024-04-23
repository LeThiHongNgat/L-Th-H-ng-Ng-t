/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baikiemtra1;

/**
 *
 * @author DELL
 */
public class SynchBank {
    public static void main(String[] args) {
        final int N_ACCOUNTS = 100;
        final double INIT_BALANCE = 1000;
        final double MAX_AMOUNT = 1000;

        Bank bank = new Bank(N_ACCOUNTS, INIT_BALANCE);

        for (int i = 0; i < N_ACCOUNTS; i++) {
            TransferMoney transferMoney = new TransferMoney(bank, i, MAX_AMOUNT);
            Thread thread = new Thread(transferMoney);
            thread.start();
        }
    }
}
