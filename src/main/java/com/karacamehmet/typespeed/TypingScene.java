package com.karacamehmet.typespeed;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TypingScene {
    private static final String[] SAMPLE_TEXTS = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean non erat ornare, consectetur mi vitae, semper felis. Curabitur suscipit eu ipsum a scelerisque. Sed volutpat semper ex in condimentum. Phasellus sed ligula bibendum, auctor ante vel, fringilla dui. Nullam ullamcorper tortor diam, id aliquet velit sollicitudin nec. Morbi in malesuada mauris. Sed ac imperdiet erat, eu rutrum ex. Proin sit amet mattis arcu. Duis id ultricies sapien.",
            "Donec ut purus imperdiet justo congue tincidunt. Integer laoreet volutpat tortor. Aliquam id risus eu dolor tincidunt cursus. Donec vulputate, risus quis molestie accumsan, leo neque dignissim ligula, sit amet tincidunt velit lacus sit amet felis. Sed sollicitudin pharetra metus, a placerat ipsum blandit nec. Phasellus scelerisque eget nisi in maximus. Morbi auctor, nisi quis blandit lobortis, nisi nunc dictum metus, eget accumsan augue augue nec diam. Phasellus metus arcu, vestibulum sit amet dictum ut, consectetur vitae velit. Sed turpis sem, gravida sed eros sit amet, eleifend ultrices metus. "
            };
    private TextFlow textFlow;
    private TextArea textArea;
    private Timeline timeline;
    private int correctWords;
    private Stage primaryStage;
    private Label timerLabel;
    private int remainingTime;


    public Scene createScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox layout=new VBox(10);
        timerLabel = new Label("Time: 60");
        textFlow=new TextFlow();
        textArea=new TextArea();
        textArea.setWrapText(true);

        String sampleText=SAMPLE_TEXTS[(int) (Math.random() * SAMPLE_TEXTS.length)];
        for (String word: sampleText.split(" ")) {
            Text text=new Text(word+" ");
            textFlow.getChildren().add(text);
        }

        textArea.textProperty().addListener((observable, oldValue, newValue) -> checkText(newValue,sampleText));

        remainingTime=60;
        timeline=new Timeline(new KeyFrame(Duration.seconds(1),e -> updateTimer()));
        timeline.setCycleCount(remainingTime);
        timeline.setOnFinished(e->{
            timeline.stop();
            Platform.runLater(this::showAlert);
        });
        timeline.play();

        layout.getChildren().addAll(timerLabel,textFlow,textArea);

        return new Scene(layout,600,400);
    }

    private void updateTimer() {
        remainingTime--;
        timerLabel.setText("Time: "+remainingTime);
    }


    private void checkText(String userInput, String sampleText) {
        String[] sampleWords=sampleText.split(" ");
        String[] userWords=userInput.split(" ");

        textFlow.getChildren().clear();
        correctWords=0;

        for (int i = 0; i < sampleWords.length; i++) {
            Text text=new Text(sampleWords[i]+" ");
            if (i< userWords.length){
                if (sampleWords[i].equals(userWords[i])){
                    text.setFill(Color.GREEN);
                    correctWords++;
                }else {
                    text.setFill(Color.RED);
                }
            }
            textFlow.getChildren().add(text);
        }

    }

    private void showAlert() {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Time's Up!");
        alert.setHeaderText("Typing Test Result");
        alert.setContentText("You typed "+correctWords+" words correctly in a minute!");
        alert.showAndWait();

        TypeSpeedApp.showPrimaryStage(primaryStage);
    }
}
