package user;

import java.io.Serializable;

import main.App;

public class Administrator extends AdminUser implements Serializable {

    private String name = super.username;
    private String pass = super.password;

    public Administrator(String username, String password) {
        super(username, password);
    }

    @Override
    public void changePassword(String username, String password) {
        System.out.println("Enter new password: ");
        String newPassword = scan.next();
        this.setPass(newPassword);
        App.adminFileWrite(App.getAdminsset());
    }

    @Override 
    public String toString() {
        return String.format("%s%n%s",
                name, pass);
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}