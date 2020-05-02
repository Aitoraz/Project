package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    public String userType;
    public String requestCode;
    public User user;
    public ArrayList<User> users = null;

    public Request(){
        this("none","end",null,null);
    }

    public Request(String requestCode, String userType){
        this(userType,requestCode,null,null);
    }

    public Request(String requestCode){
        this("none",requestCode,null,null);
    }

    public Request(String requestCode, User user) {
        this.requestCode = requestCode;
        this.user = user;
    }

    public Request(String userType, String requestCode, User user, ArrayList<User> users) {
        this.userType = userType;
        this.requestCode = requestCode;
        this.user = user;
        this.users = users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public String toString()    {
        return "Request{" +
                "userType='" + userType + '\'' +
                ", requestCode='" + requestCode + '\'' +
                '}';
    }
}
