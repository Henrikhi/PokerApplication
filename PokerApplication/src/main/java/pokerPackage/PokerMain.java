package pokerPackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PokerMain extends Application {

    private Scene frontPage;
    private Scene game;
    private Scene doubling;
    private final int width = 800;
    private final int height = 600;

    public static void main(String[] args) {
        System.out.println("hello world!");
        launch(PokerMain.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("The application is running");
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        //Log in Screen
        VBox frontPageLayout = new VBox();
        TextField accountName = new TextField("account name");
        accountName.setMaxWidth(width / 4);
        TextField password = new TextField("password");
        password.setMaxWidth(width / 4);
        Button logIn = new Button("log in");
        Button createAccount = new Button("create account");
        Button exit = new Button("exit");
        frontPageLayout.getChildren().addAll(accountName, password, logIn, createAccount, exit);
        frontPageLayout.setPadding(new Insets(20));
        frontPageLayout.setSpacing(20);
        frontPage = new Scene(frontPageLayout, width, height);
        exit.setOnMouseClicked(exitClicked -> {
            System.exit(0);
        });
        logIn.setOnMouseClicked(logInClicked -> {
            //log in does not yet check if account is made or not.
            primaryStage.setScene(game);
        });

        //primary game scene
        BorderPane gameLayout = new BorderPane();
        HBox bottomButtons = new HBox();
        Button stop = new Button("stop");
        Button collect = new Button("collect");
        Button bet = new Button("bet");
        Button doubleButton = new Button("double");
        Button play = new Button("play");
        bottomButtons.getChildren().addAll(stop, collect, bet, doubleButton, play);
        gameLayout.setBottom(bottomButtons);
        game = new Scene(gameLayout, width, height);
        stop.setOnMouseClicked(stopClicked -> {
        //log out the user!
        primaryStage.setScene(frontPage);
        });

        primaryStage.setScene(frontPage); //app starts with frontpage scene
        primaryStage.setTitle("Poker application by Henrik Hirvonen");
        primaryStage.show();
    }

}
