package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class DoctorController{
    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField report;
    @FXML
    private Button heal_button;
    @FXML
    private Label topLabel;
    @FXML
    private Button exit_button;

    @FXML
    private TextField screen;
    @FXML
    private TableView<User> table;
    @FXML
    private Label added_button;

    @FXML
    private TableColumn<User, String> col_namePatient;

    @FXML
    private TableColumn<User, String> col_problem;

    @FXML
    private TableColumn<User, String> col_nameDoctor;
    @FXML
    void initialize() {
        topLabel.setText("Hello, " + Main.user.getFirstName());
        Request request = new Request("showPatient");
        try {
            Main.out.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initData();
        col_nameDoctor.setCellValueFactory(new PropertyValueFactory<>("nameDoctor"));
        col_problem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        col_namePatient.setCellValueFactory(new PropertyValueFactory<>("namePatient"));
        table.setItems(usersData);
        heal_button.setOnAction(event -> {
            User person = table.getSelectionModel().getSelectedItem();
            Request r = new Request("delete",person.getNameDoctor() + " " + person.getNamePatient() + " " +person.getProblem());
            try {
                Main.out.writeObject(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());

            added_button.setText("Patient is healed!");
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

    private void initData()  {
        try {
            ArrayList<User> problems = (ArrayList<User>)Main.in.readObject();
            for(User u:problems){
                if(u.getNameDoctor().equals(Main.user.getFirstName())) {
                    usersData.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displaySelected(MouseEvent mouseEvent) {
        User person = table.getSelectionModel().getSelectedItem();
        if(person == null){
            screen.setText("...");
        }
        else{
            String name = person.getNamePatient() + "        " + person.getProblem();
            screen.setText(name);
        }
    }
}