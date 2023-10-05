package FileHandling;

public class User {
    private String username, password, role;
    private Name name;

    public User(String[] stringArr){
        this.role = stringArr[0];
        this.username = stringArr[1];
        this.password = stringArr[2];
        this.name = new Name(stringArr[3]);
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
