package user;

import java.io.Serializable;
import java.util.InputMismatchException;

import main.App;

public class NormUser extends NormalUser implements Serializable {

    private String nameUser = super.username;
    private String pass = super.password;
    private int total = super.balance;
    
    public NormUser(String username, String password, int balance) {
        super(username, password);
        this.total = balance;
    }
    
    public NormUser(String username, String password) {
        super(username, password);
    }

    @Override
    public void changePassword(String username, String password) {
        System.out.println("Enter new password: ");
        String newPassword = scan.next();
        this.setPass(newPassword);
        App.userFileWrite(App.getUsersset());
    }

    @Override
    public void checkBalance() {
        System.out.println("Your Current balance is: " + total);
    }

    @Override
    public void withdraw() {
        while (true) {
            System.out.println("Enter Withdraw Amount");
            int withdraw = 1;
            try {
                withdraw = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                scan.reset();
                scan.next();
                continue;
            }
            scan.reset();
            if (withdraw > 0 && withdraw < this.total) {
                this.total -= withdraw;
                this.checkBalance();
                App.userFileWrite(App.getUsersset());
                break;
            } else if (this.total == 0) {
                System.out.println("You Have Insufficient Balance");
                scan.reset();
                this.userMenu();
                break;
            } else if (withdraw > this.total) {    
                System.out.println("Please Withdraw a Less Amount");
                scan.reset();
            } else if (withdraw == 0) {
                System.out.println("You Cannot Withdraw O");
                scan.reset();
            } else if (withdraw < 0) {
                System.out.println("You Cannot Withdraw an Amount less than 0");
                scan.reset();
            }
        }
    }

    @Override 
    public String toString() {
        return String.format("%s%n%s%n%d",
                nameUser, pass, total);
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public int getBalance() {
        return total;
    }
    @Override
    public void setBalance(int balance) {
        this.total = balance;
    }
}