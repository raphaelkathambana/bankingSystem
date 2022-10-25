package user;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

public class DeSerialize {
    public static void main(String[] args) {
        NormUser c = null;
        Administrator b = null;

        try (FileInputStream inFile = new FileInputStream("serializedUsers.dat");
        ObjectInputStream in = new ObjectInputStream(inFile);) {
            c = (NormUser) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (FileInputStream inFile = new FileInputStream("serializedAdmins.dat");
        ObjectInputStream in = new ObjectInputStream(inFile);) {
            b = (Administrator) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Optional<NormUser> d = Optional.ofNullable(c);
        String ans1 = d
            .map(NormUser::toString)
            .orElse("Did not work out :/");
            Optional<Administrator> e = Optional.ofNullable(b);
        String ans2 = e
                .map(Administrator::toString)
                .orElse("Did not work out :/");
        System.out.println(ans1);
        System.out.println(ans2);
    }
}