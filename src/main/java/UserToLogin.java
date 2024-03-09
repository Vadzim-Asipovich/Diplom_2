public class UserToLogin {
    private String email;
    private String password;

    public UserToLogin() {
    }

    public UserToLogin(String email, String name) {
        this.email = email;
        this.password = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}