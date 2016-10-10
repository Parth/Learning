package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #336699;");

        HBox top = new HBox();
        top.setMinHeight(50);
        top.setMinWidth(600);

        TextField input = new TextField();
        input.setMinSize(550, 50);

        TextField output = new TextField();
        Button go = new Button("Go");
        go.setMinSize(50, 50);
        go.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = input.getText();
                output.setText(url);
            }
        });

        top.getChildren().addAll(input, go);

        output.setPrefHeight(550);
        output.setPrefWidth(600);

        root.getChildren().addAll(top, output);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
