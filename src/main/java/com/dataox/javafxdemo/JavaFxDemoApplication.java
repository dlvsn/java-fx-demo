package com.dataox.javafxdemo;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import static javafx.geometry.Pos.CENTER;
import static javafx.stage.StageStyle.UNDECORATED;
import static javafx.stage.StageStyle.UTILITY;

@SpringBootApplication
public class JavaFxDemoApplication extends Application {
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        LauncherImpl.launchApplication(JavaFxDemoApplication.class, args);
    }

    @Override
    public void init() {
        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(JavaFxDemoApplication.class);
        applicationBuilder.headless(false);
        context = applicationBuilder.run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);

        primaryStage.setOpacity(0);
        primaryStage.initStyle(UTILITY);
        primaryStage.show();

        Stage stage = new Stage(UNDECORATED);
        stage.setResizable(false);
        stage.initOwner(primaryStage);

        Label statusLabel = new Label();
        statusLabel.setPrefWidth(300);
        statusLabel.setPrefHeight(8);
        statusLabel.setFont(new Font(13));
        statusLabel.setAlignment(CENTER);

        Label infoLabel = new Label();
        infoLabel.setPrefWidth(300);
        infoLabel.setPrefHeight(8);
        infoLabel.setFont(new Font(11));
        infoLabel.setAlignment(CENTER);

        Button exit = new Button("OK");
        exit.setAlignment(CENTER);
        exit.setPrefWidth(80);
        exit.setDisable(false);
        exit.setOnMouseClicked(e -> stop());

        VBox parent = new VBox(5);
        parent.setAlignment(CENTER);

        parent.getChildren().addAll(statusLabel, infoLabel, exit);
        parent.setPadding(new Insets(5, 5, 5, 5));
        stage.setScene(new Scene(parent));

        TestService bean = context.getBean(TestService.class);
        String message = bean.sendStartMessage();
        infoLabel.setText(message);

        stage.show();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
