package pojo_withoutBuilder;

public class LoginBody {
    private String email;
    private String password;

    public LoginBody() {}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}


