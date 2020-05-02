package sample;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

public class PatientController {

    private ObservableList<User> usersData = FXCollections.observableArrayList();
    private ObservableList<User> usersDataProblem = FXCollections.observableArrayList();
    private TableView<User> user;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit_button;

    @FXML
    private Button enroll_button;

    @FXML
    private Label topLabel;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User,String > col_first;

    @FXML
    private TableColumn<User,String> col_last;

    @FXML
    private TableColumn<User,String> col_login;

    @FXML
    private TableColumn<User,String> col_password;

    @FXML
    private TableColumn<User,String> col_usertype;
    @FXML
    private TextField screen;
    @FXML
    private TextArea problem_text;
    @FXML
    private Button show_button;
    @FXML
    private Label added_button;
    @FXML
    private TableView<User> tableProblem;

    @FXML
    private TableColumn<User,String> col_namePatient;

    @FXML
    private TableColumn<User, String> col_problem;

    @FXML
    private TableColumn<User, String> col_nameDoctor;



    @FXML
    void initialize() {
        topLabel.setText("Hello, " + Main.user.getFirstName());
        Request request = new Request("showdoctor");
        try {
            Main.out.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initData();
        col_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_usertype.setCellValueFactory(new PropertyValueFactory<>("userType"));
        table.setItems(usersData);
        show_button.setOnAction(event -> {
            Request r =  new Request("show_Problems_Patient");
            try {
                Main.out.writeObject(r);

            } catch (IOException e) {
                e.printStackTrace();
            }
            initDataProblem();
            col_namePatient.setCellValueFactory(new PropertyValueFactory<>("namePatient"));
            col_nameDoctor.setCellValueFactory(new PropertyValueFactory<>("nameDoctor"));
            col_problem.setCellValueFactory(new PropertyValueFactory<>("problem"));
            //time_colum.setCellValueFactory(new PropertyValueFactory<>("time"));
            tableProblem.setItems(usersDataProblem);

        });
        enroll_button.setOnAction(event -> {
            String problem = problem_text.getText();
            String nameDoctor = screen.getText();
            String namePatient = Main.user.getFirstName();
            Request r = new Request("add_problems", problem + " " + nameDoctor + " " + namePatient);
            try {
                ObjectOutputStream out = Main.out;
                out.writeObject(r);
            } catch (IOException e) {
                System.out.println("Can't request login");
            }
            added_button.setText("Patient is enrolled!");
            updated();
        });
        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/loginpage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

    }

    private void updated() {
        usersDataProblem.clear();
        User person = table.getSelectionModel().getSelectedItem();
        usersDataProblem.add(person);
        tableProblem.setItems(usersDataProblem);
    }

    private void initDataProblem() {
        try {
            ArrayList<User> user = (ArrayList<User>)Main.in.readObject();
            for(User u :user){
                if(u.getNamePatient().equals(Main.user.getFirstName())){
                    usersDataProblem.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initData() {
        try {
            ArrayList<User> users = (ArrayList<User>) Main.in.readObject();
            for(User u:users) {
                if(u.getUserType().equals("doctor")) {
                    usersData.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void displaySelected(javafx.scene.input.MouseEvent mouseEvent) {
        User person = table.getSelectionModel().getSelectedItem();
        if(person == null){
            screen.setText("Name...");
        }
        else{
            String name = person.getFirstName() + " " + person.getLastName();
            screen.setText(name);
        }
    }
}
