package data;

public class Administrator implements IPerson {
    private String name;
    private String password;
    private  long id;

    public Administrator(String name, String password, long id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public Administrator(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
