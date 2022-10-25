package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import user.AdminUser;
import user.Administrator;
import user.NormUser;
import user.NormalUser;

public class App {
    public static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<NormalUser> usersList = new ArrayList<>();
    private static final ArrayList<AdminUser> adminsList = new ArrayList<>();
    private static final Set<AdminUser> adminsSet = new HashSet<>();
    private static final Set<NormalUser> usersSet = new HashSet<>();
    private static final ArrayList<Integer> moneys = new ArrayList<>();

    public static void main(String[] args) throws NullPointerException{
        fileReader();
        loginHandler(welcomeScreen());
    }

    public static NormalUser makeUser(String username, String password) {
        return new NormUser(username, password);

    }
    public static NormalUser makeUser(String username, String password, int money) {
        return new NormUser(username, password, money);
    }
    public static AdminUser makeAdmin(String username, String password) {
        return new Administrator(username, password);

    }

    public static void loginHandler(int level) {
        int failed = 0;
        if (level == 1) {//user
            System.out.println("Enter Username");
            String name = scanner.next();
            for (NormalUser user : usersSet) {
                if (user.getUsername().equals(name)) {
                    failed = user.login();
                    break;
                } 
            }
        } else if (level == 2) {//admin
            System.out.println("Enter Username");
            String name = scanner.next();
            for (AdminUser admin : adminsSet) {
                if (admin.getUsername().equals(name)) {
                    failed = admin.login();
                    break;
                } 
            }
            
        }
        if (failed == 0 ) {
            System.out.println("Invalid username");
            loginHandler(level);
        }
    }

    public static void fileReader() {
        usersSet.clear();
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("userData.txt")))) {
            String input;
            String nameUser = "";
            String passwordUser = "";
            int money;
            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                nameUser = input;
                input = scanner.nextLine();
                passwordUser = input;
                money = scanner.nextInt();
                moneys.add(money);
                usersSet.add(makeUser(nameUser, passwordUser, money));
                input = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("adminData.txt")))) {
            String input;
            String nameUser = "";
            String passwordUser = "";
            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                nameUser = input;
                input = scanner.nextLine();
                passwordUser = input;
                adminsSet.add(makeAdmin(nameUser, passwordUser));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void userFileWrite(Set<NormalUser> users) {
        try (BufferedWriter outfile = new BufferedWriter(new FileWriter("userData.txt"));) {
            for (NormalUser user : users) {
                outfile.write(user.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileReader();
    }
    public static void adminFileWrite(Set<AdminUser> admins) {
        try (BufferedWriter outfile = new BufferedWriter(new FileWriter("adminData.txt"));) {
            for (AdminUser admin : admins) {
                outfile.write(admin.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileReader();
    }
   
    public static int welcomeScreen() {
        int ans = 0;
        System.out.println("---------------------Hello!-------------------------");
        System.out.println("---------WELCOME TO KERPERIAS NATIONAL BANK---------");
        System.out.println("-----What is your Position in the bank?-----");
        System.out.println("1.User");
        System.out.println("2.Administrator");
        while (true) {
            System.out.println("(Enter Either 1 or 2)");
            try {
                ans = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong Input not number");
            }
            scanner.nextLine();
            if (ans == 2 || ans == 1) {
                break;
            } else {
                System.out.println("Wrong Input 1 or 2");
            }
        }
        
        return ans;
    }

    public static Set<NormalUser> getUsersset() {
        return usersSet;
    }
    public static List<NormalUser> getUserslist() {
        return usersList;
    }
    public static Set<AdminUser> getAdminsset() {
        return adminsSet;
    }
    public static List<Integer> getMoneys() {
        return moneys;
    }
    public static List<AdminUser> getAdminslist() {
        return adminsList;
    }
}