package FileHandling;

public abstract class User {
    private String username, password, role;
    private Name name;

    public User(String role, String username, String password, String name){
        this.role = role;
        this.username = username;
        this.password = password;
        this.name = new Name(name);
    }

    public String getRole() { return this.role;}
    public String getName() { return this.name.toString();}

    public int tryLogin(String username, String password){
        if (this.username.compareTo(username) == 0){
            if (this.password.compareTo(password) == 0) return 200;
            else return 406;
        }else return 404;
    }
}
