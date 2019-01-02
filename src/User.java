import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String passWord;
    private boolean fullAccess;

    public User(String userName, String passWord, boolean fullAccess) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullAccess = fullAccess;
    }

    public String getUserName() {return userName;}
    public String getPassWord() {return passWord;}
    public void setPassWord(String passWord) {this.passWord = passWord;}
    public boolean getFullAccess() {return fullAccess;}
}
