package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.tagsController.HashController;


public class Controller {

    InstaBot bot;

    @FXML
    Label labelStatus;

    @FXML
    TextField usrname;

    @FXML
    PasswordField password;

    @FXML
    public void login(ActionEvent event) throws Exception{
        bot = new InstaBot(usrname.getText(), password.getText());
        bot.toLogin();
        Thread.sleep(1000);

        if (bot.getDriver().getTitle().equals("Войти • Instagram")) {
            labelStatus.setText("Try again");
            labelStatus.setTextFill(Color.valueOf("#DD2A23"));
        } else {
            labelStatus.setText("Successful");
            labelStatus.setTextFill(Color.valueOf("#53EE31"));
            HashController hashController = hashWindow().getController();
            hashController.setHashBot(bot);
        }

    }

    public FXMLLoader hashWindow() throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../hashPage.fxml"));
        loader.load();

        Parent hashWindow = loader.getRoot();
        Stage window = new Stage();
        window.setTitle("Hashtags");
        window.setScene(new Scene(hashWindow, 700,400));
        window.show();
        return loader;

    }

}
