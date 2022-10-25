package user;

import java.util.Scanner;

import main.App;

public class AdminUser implements User {
    String username = "";
    String password = "";
    Scanner scan = new Scanner(System.in);

    public AdminUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public AdminUser() {
    }

    public void adminmenu() { //2
        int ans = 0;
        System.out.println("__________________________________________________");
        System.out.println("ADMIN SCREEN");
        System.out.println("What would you like to do?");
        System.out.println("1.Deposit Money for A Client");
        System.out.println("2.Register a New Client");
        System.out.println("3.Reset Password");
        System.out.println("4.Quit");
        while (true) {
            System.out.println("Enter the Choice You'd like to Proceed with: ");
            try {
                ans = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong Input not number");
            }
            scan.nextLine();
            if (ans == 2 || ans == 1 || ans == 3 || ans == 4) {
                menuSwitcher(ans);
                break;
            } else {
                System.out.println("Wrong Option Entered");
            }
        }
    }

    public int login() {
        int ans = 0;
        System.out.println("Enter Your password: ");
        String pass = scan.next();
        if (this.password.equals(pass)) {
                System.out.println("Login successful");
                ans = 1;
                this.adminmenu();
        } else {
            System.out.println("Invalid password");
        }
        if (ans == 0) {
            this.login();
            ans = 1;
        }
        return ans;
    }

    public void menuSwitcher (int b) {
        switch (b) {//admin
            case 1:
                this.depositMoney();
                this.adminmenu();
                break;
            case 2:
                this.registrationPage();
                this.adminmenu();
                break;
            case 3:
                this.changePassword(this.getUsername(), this.getPassword());
                App.getAdminslist().add(this);
                System.out.println(App.getAdminslist().toString());
                this.login();
                break;
            case 4:
                System.out.println("I hope you enjoyed the process");
                break;
            default:
                System.out.println("Invalid Character Entered on Menu");
                this.adminmenu();
                break;
        }
    }

    public void depositMoney() {
        int failed = 0;
        System.out.println("Enter user's Name");
        String user = scan.nextLine();
        for (NormalUser a : App.getUsersset()) {
            if (a.getUsername().equals(user)) {
                System.out.println("Enter Money to deposit");
                int depositMoney = scan.nextInt();
                a.setBalance(a.getBalance() + depositMoney);
                App.getMoneys().add(a.getBalance());
                App.getUsersset().add(a);
                App.userFileWrite(App.getUsersset());
                failed = 1;
                break;
            }
        }
        if (failed == 0 ) {
            System.out.println("Invalid Username");
            this.depositMoney();
        }
    }

    public void registrationPage() {
        System.out.println("Enter new Username");
        String newName = scan.nextLine();
        System.out.println("Enter new Password");
        String newPassword = scan.nextLine();
        App.getUsersset().add(App.makeUser(newName, newPassword));
        App.userFileWrite(App.getUsersset());
        App.fileReader();
    }

    public void changePassword(String username, String password) {
        System.out.println("Enter new password: ");
        String newPassword = scan.next();
        this.setPassword(newPassword);
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
}
