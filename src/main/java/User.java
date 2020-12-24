import java.util.Objects;

public class User implements UserDAO {
    private String userName;
    private String userLastName;
    private String userNickName;
    private String userPass;

    public User(String userName, String userLastName, String userNickName, String userPass) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userNickName = userNickName;
        this.userPass = userPass;
    }

    public User(String userName, String userNickName, String userPass) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.userPass = userPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserName().equals(user.getUserName())
                && getUserNickName().equals(user.getUserNickName())
                && getUserPass().equals(user.getUserPass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(),
                getUserNickName(),
                getUserPass());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
