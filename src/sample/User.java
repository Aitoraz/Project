package sample;

import java.io.Serializable;

public class User  implements Serializable {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String userType;
    private String nameDoctor;
    private String namePatient;
    private String problem;



    public User(String nameDoctor,String namePatient,String problem){
        this.nameDoctor = nameDoctor;
        this.namePatient = namePatient;
        this.problem = problem;
    }

    public User(String firstName, String lastName, String login, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }



    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", nameDoctor='" + nameDoctor + '\'' +
                ", namePatient='" + namePatient + '\'' +
                ", problem='" + problem + '\'' +
                '}';
    }
}
