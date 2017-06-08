package model;

import org.json.JSONObject;

/**
 * Created by mevur on 6/5/2017.
 */
public class User {
    private String id;
    private String username;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        try {
            object.put("id", id);
            object.put("username", username);
            object.put("password", password);
            return object.toString();
        } catch (Exception e) {
            return super.toString();
        }
    }
}
