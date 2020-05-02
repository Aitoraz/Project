package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private Connection conn = null;
    private Connection con = null;

    public ClientHandler(Socket socket, Connection conn) {
        this.socket = socket;
        this.conn = conn;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUser(Request request){
        User u = null;
        StringTokenizer st = new StringTokenizer(request.getUserType());
        String firstname = st.nextToken();
        String lastname = st.nextToken();
        String login = st.nextToken();
        String password = st.nextToken();
        String usertype = st.nextToken();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO mydb (id, firstName,lastName,login,password,userType) VALUES(NULL, ?, ?,?,?,?)");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, login);
            ps.setString(4, password);
            ps.setString(5, usertype);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<User> getUsers() {
        ArrayList<User> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mydb");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String userType = rs.getString("userType");

                list.add(new User(firstName,lastName,login, password,userType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private User login(Request request){
        User u = null;
        StringTokenizer st = new StringTokenizer(request.getUserType());
        String login = st.nextToken();
        String password = st.nextToken();
        String statement = "SELECT * FROM mydb WHERE login = \"" + login  + "\" AND password = \"" + password + "\"";
        try{
            ResultSet rs = conn.prepareStatement(statement).executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("firstName");
                String surname = rs.getString("lastName");
                String slogin = rs.getString("login");
                String spassword = rs.getString("password");
                String userType = rs.getString("userType");
                u = new User(name,surname,slogin,spassword,userType);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<User> getProblems() {
        ArrayList<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM problems");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String problem = rs.getString("problem");
                String nameDoctor = rs.getString("nameDoctor");
                String namePatient = rs.getString("namePatient");
                //String time = rs.getString("time");
                list.add(new User(nameDoctor,namePatient,problem));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private User selectProblems(Request request) {
        User u = null;
        StringTokenizer st = new StringTokenizer(request.getUserType());
        String problem = st.nextToken();
        String nameDoctor = st.nextToken();
        String nameDoctor1 = st.nextToken();
        String namePatient = st.nextToken();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO problems (id,problem,nameDoctor,namePatient) VALUES(NULL,?,?,?)");
            ps.setString(1,problem);
            ps.setString(2,nameDoctor);
            ps.setString(3,namePatient);
            u = new User(problem,nameDoctor,namePatient);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void run() {
        Request r = null;
        do{
            try {
                r = (Request) ois.readObject();
                if (r.getRequestCode().equals("end")) {
                    System.out.println("Ending...");
                } else if (r.getRequestCode().equals("login")) {
                    System.out.println("Logging: " + r.toString());
                    oos.writeObject(login(r));
                }else if(r.getRequestCode().equals("register")){
                    addUser(r);
                    System.out.println("New user: " + r.toString());
                }else if(r.getRequestCode().equals("add_problems")){
                    selectProblems(r);
                    System.out.println("enrolled: " + r.toString());
                }else if(r.getRequestCode().equals("showdoctor")){
                    oos.writeObject(getUsers());
                }else if(r.getRequestCode().equals("showPatient")){
                    oos.writeObject(getProblems());
                }else if(r.getRequestCode().equals("delete")){
                    deleteProblems(r);
                    System.out.println("Healed:" + r.toString());
                }else if(r.getRequestCode().equals("show_Problems_Patient")){
                    oos.writeObject(getProblems());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }while(!r.getRequestCode().equals("end"));
    }

    private void deleteProblems(Request request) {
        StringTokenizer st = new StringTokenizer(request.getUserType());
        String nameDoctor = st.nextToken();
        String namePatient = st.nextToken();
        String problem = st.nextToken();
        try {
            conn.prepareStatement("DELETE FROM problems where problem = \"" + problem  + "\" AND namePatient = \"" + namePatient + "\"").executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }


}
