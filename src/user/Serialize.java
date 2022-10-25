package user;

import java.io.*;

public class Serialize {
    public static void main(String[] args) {
        NormUser c = new NormUser("Harry", "Harry", 10000);
        Administrator b = new Administrator("Bob", "Bob");

        try (FileOutputStream outFile = new FileOutputStream("serializedUsers.dat");
        ObjectOutputStream out = new ObjectOutputStream(outFile);) {
            out.writeObject(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream outFile = new FileOutputStream("serializedAdmins.dat");
        ObjectOutputStream out = new ObjectOutputStream(outFile);) {
            out.writeObject(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
