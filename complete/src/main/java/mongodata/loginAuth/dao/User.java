package mongodata.loginAuth.dao;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    public
    String userName;
    public String password;
    public String role;

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}