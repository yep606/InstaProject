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

    public void submit() {

        String[] hastags = hashArea.getText().split(" ");
        List<String> hashTags = new ArrayList<>(Arrays.asList(hastags));
        hashBot.setHashtags(hashTags);
        while(!hashBot.getHashtags().isEmpty())
            hashBot.likePhoto();

    }

    public void setHashBot(InstaBot hashBot) {
        this.hashBot = hashBot;
    }


}
