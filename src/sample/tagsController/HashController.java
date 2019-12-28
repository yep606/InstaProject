package sample.tagsController;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import sample.InstaBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HashController {

    private InstaBot hashBot;

    @FXML
    public TextArea hashArea;

    @FXML
    public TextArea commArea;

    @FXML
    RadioButton setButton;

    boolean isClicked;

    public void submit() {
        isClicked = setButton.isPressed();
        startTask();
    }

    public void startTask() {

        Thread action = new Thread(() -> {

            String[] tags = hashArea.getText().split(" ");
            List<String> hashTags = new ArrayList<>(Arrays.asList(tags));
            tags = commArea.getText().split(",");
            List<String> comments = new ArrayList<>(Arrays.asList(tags));

            hashBot.setHashtags(hashTags);
            hashBot.setComments(comments);

            while (!hashBot.getHashtags().isEmpty())
                hashBot.likeOrCommentPhoto();

        });
        action.start();

    }

    public void setHashBot(InstaBot hashBot) {
        this.hashBot = hashBot;
    }


}
