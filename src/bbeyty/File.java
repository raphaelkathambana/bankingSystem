package bbeyty;

public class File {
    private String name;
    private int size;
    public String sus;

    public static int age = 10;


    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
    

    public File(String name, int size, String sus) {
        this.name = name;
        this.size = size;
        this.sus = sus;
    }


    public void introduce() {
        System.out.println("My name is " + this.name);
    }

    public void introduce(String name) {
        System.out.println("My name is " + name + " size is " + this.size);
    }
    public String getName() {
        age = 11;
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    
}
