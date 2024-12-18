package Backend;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String status;
    private ArrayList<User> friends;

    public User(String id, String username, String password, String email, LocalDate dateOfBirth, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = String.valueOf(dateOfBirth);
        this.status = status;
        this.friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public String getId() {
        return id;
    }

    public void addFriend(User user) {
        friends.add(user);
    }
    public void removeFriend(User user) {
        for (User friend : friends) {
            if (friend.getId().equals(user.getId())) {
                friends.remove(friend);
                return;
            }
        }
    }
}
