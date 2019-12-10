package sample.tagsController;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sample.InstaBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HashController{

    private InstaBot hashBot;

    @FXML
    public TextArea hashArea;

    @FXML
    public TextArea commArea;

    public void submit() {

        String[] tags = hashArea.getText().split(" ");
        List<String> hashTags = new ArrayList<>(Arrays.asList(tags));
        tags = commArea.getText().split(",");
        List<String> comments = new ArrayList<>(Arrays.asList(tags));

        hashBot.setHashtags(hashTags);
        hashBot.setComments(comments);
        while(!hashBot.getHashtags().isEmpty())
            hashBot.likeOrCommentPhoto();

    }

    public void setHashBot(InstaBot hashBot) {
        this.hashBot = hashBot;
    }


}
