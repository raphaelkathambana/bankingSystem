package user;

import java.io.Serializable;

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
        System.out.println("Enter Withdraw Amount");
        int withdraw = scan.nextInt();
        if (withdraw > 0 && withdraw < this.total) {
            this.total -= withdraw;
            this.checkBalance();
            App.userFileWrite(App.getUsersset());
        } else if (this.total == 0) {
            System.out.println("You Have Insufficient Balance");
        } else if (withdraw > this.total) {    
            System.out.println("Please Withdraw a Less Amount");
        } else {
            System.out.println("You Can't Withdraw less than 0");
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