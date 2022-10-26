package user;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.App;

public class NormalUser implements User{
    String username = "";
    String password = "";
    int balance = 0;

    Scanner scan = new Scanner(System.in);
    public NormalUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public NormalUser() {
    }

    public void menuSwitcher (int b) {
        switch (b) {//user
        case 1:
            this.withdraw();
            App.getUserslist().add(this);
            break;
        case 2:
            this.checkBalance();
            this.userMenu();
            break;
        case 3:
            this.changePassword(this.getUsername(), this.getPassword());
            App.getUserslist().add(this);
            this.login();
            break;
        case 4:
            System.out.println("I hope you enjoyed the process");
            break;
        default:
            System.out.println("Invalid Role Entered on menu");
            this.userMenu();
            break;
        }
    }

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
            if (withdraw > 0 && withdraw < this.balance) {
                this.balance -= withdraw;
                this.checkBalance();
                App.userFileWrite(App.getUsersset());
                break;
            } else if (this.balance == 0) {
                System.out.println("You Have Insufficient Balance");
                scan.reset();
                this.userMenu();
                break;
            } else if (withdraw > this.balance) {    
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

    public void userMenu() {//1
        int ans = 0;
        System.out.println("__________________________________________________");
        System.out.println("USER SCREEN");
        System.out.println("What would you like to do?");
        System.out.println("1.Withdraw Cash");
        System.out.println("2.Check Balance");
        System.out.println("3.Reset Password");
        System.out.println("4.Quit");
        while (true) {
            System.out.println("Enter the Choice You'd like to Proceed with: ");
            try {
                ans = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong Input: Not Number");
            }
            scan.nextLine();
            if (ans == 2 || ans == 1 || ans == 3 || ans == 4) {
                menuSwitcher(ans);
                break;
            } else {
                System.out.println("Invalid Option Entered");
            }
        }
    }
    
    public void checkBalance() {
        System.out.println("Your current balance is: " + balance);
    }

    public int login() {
        int ans = 0;
        System.out.println("Enter Your Current Password: ");
        String pass = scan.next();
        if (this.password.equals(pass)) {
                System.out.println("Login Successful");
                ans = 1;
                this.userMenu();
        } else {
            System.out.println("Invalid Password");
        }
        if (ans == 0) {
            this.login();
            ans = 1;
        }
        return ans;
    }
    
    public void changePassword(String username, String password) {
        System.out.println("Enter new password: ");
        String newPassword = scan.next();
        this.setPassword(newPassword);
        App.userFileWrite(App.getUsersset());
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}