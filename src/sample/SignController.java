package sample;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;

public class SignController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField login_text_field;

    @FXML
    private PasswordField password_text_field;

    @FXML
    private Button sign_up_button;

    @FXML
    private TextField userType_field;

    @FXML
    private Button back_button;

    @FXML
    private Label added_button;

    @FXML
    void signup(ActionEvent event) {

    }

    @FXML
    void initialize() {
        sign_up_button.setOnAction(event -> {
            String firstname = firstName.getText();
            String lastname = lastName.getText();
            String login = login_text_field.getText();
            String password = password_text_field.getText();
            String type = userType_field.getText();
            Request r = new Request("register",firstname + " " + lastname+ " " + login + " "+ password + " " + type );

            try{
                ObjectOutputStream out = Main.out;
                out.writeObject(r);
            }catch (IOException e){
                System.out.println("Can't request login");
            }
            added_button.setText("User is added!");

        });


        back_button.setOnAction(event -> {
            back_button.getScene().getWindow().hide();

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
}
