package sample;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_text_field;

    @FXML
    private PasswordField password_text_field;

    @FXML
    private Button sign_up_button;

    @FXML
    private Button logIn_button;

    @FXML
    private TextField userType_field;
    @FXML
    private Label empty_text;

    @FXML
    void initialize ()     {

        sign_up_button.setOnAction(event -> {
            sign_up_button.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/signUp.fxml"));
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

        logIn_button.setOnAction(event -> {
            String login = login_text_field.getText();
            String password = password_text_field.getText();
            String type = userType_field.getText();
            if(login.equals("") && password.equals("") && type.equals("")){
                empty_text.setText("Wrong password or login!");
            }
            else {
                Request r = new Request("login", login + " " + password);

                try {
                    Main.out.writeObject(r);
                    try {
                        Main.user = (User) Main.in.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (Main.user != null) {
                        if (Main.user.getUserType().equals("doctor")) {
                            System.out.println("Logged in");
                            logIn_button.getScene().getWindow().hide();

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/sample/doctorpage.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        }
                        if (Main.user.getUserType().equals("patient")) {
                            System.out.println("Logged in");
                            logIn_button.getScene().getWindow().hide();

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/sample/patientpage.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();

                        }

                    } else {
                        System.out.println("Wrong password or login");
                    }

                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Can't request login");
                }
            }
        });

    }
}
