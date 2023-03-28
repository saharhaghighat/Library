import java.time.LocalDate;

public class Account {
    private String userName;
    private String password;
    private LocalDate createdAt;

    public Account(String userName,String password){
        this.userName=userName;
        setPassword(password);
        this.createdAt=LocalDate.now();
    }
    public void setPassword(String password){
        if (password.length()>5){
        this.password=password;}
        else
            throw new IllegalArgumentException("Invalid password");
    }
    public boolean ValidatePassword(String password){
        if (this.password.equals(password))
            return true;
        else
            return false;

    }

}
