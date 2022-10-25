package bbeyty;

public class Main {
    public static void main(String[] args) {
        File f1 = new File("test1", 12, "Jerry");
        FileBaby f2 = new FileBaby("test2", 2, "Sweets");

        f1.getName();
        f2.introduce();
        System.out.println(f2.sus);
    }
}
