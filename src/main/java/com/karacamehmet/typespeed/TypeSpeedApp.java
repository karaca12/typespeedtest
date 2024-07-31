package com.karacamehmet.typespeed;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TypeSpeedApp extends Application {
    @Override
    public void start(Stage primaryStage){
        showPrimaryStage(primaryStage);
    }

    public static void showPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Type Speed Test");
        Button startButton = new Button("Start Typing Test");
        startButton.setOnAction(e -> {
            TypingScene typingScene = new TypingScene();
            primaryStage.setScene(typingScene.createScene(primaryStage));
        });

        VBox layout = new VBox(10);
        layout.getChildren().add(startButton);
        Scene scene=new Scene(layout,400,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
