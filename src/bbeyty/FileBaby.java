package bbeyty;

public class FileBaby extends File {
    private String name;
    private int size;
    private String content;
    public FileBaby(String name, int size, String content) {
        super(name, size);
        this.content = content;
    }


    @Override
    public void introduce() {
        System.out.println("My content is " + content);
        super.sus = "Jerry";
    }

    
}
