package poker.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    private poker.logic.GameLogics logic = new poker.logic.GameLogics();

    public static void main(String[] args) {
        launch(PokerMain.class);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
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
            //log in does not yet check if account exists or not.
            primaryStage.setScene(game);
        });

        //primary game screen
        BorderPane gameLayout = new BorderPane();
        HBox bottomButtons = new HBox();
        HBox cardButtonsHBox = new HBox();
        
        Label winningsLabel = new Label("credits: " + logic.winnings / 100);
        winningsLabel.setPadding(new Insets(20));
        gameLayout.setTop(winningsLabel);

        //bottombuttons of the gaming screen
        Button stop = new Button("stop");
        Button collect = new Button("collect");
        Button bet = new Button("bet: " + logic.bet / 100);
        Button doubleButton = new Button("double");
        Button play = new Button("play");

        //card graphics
        for (int i = 0; i < 5; i++) {
            logic.cardButtons.add(new Button());
        }
        
        logic.cardButtons.forEach(card -> {
            card.setPrefSize(width / 10, height / 5);
            cardButtonsHBox.getChildren().add(card);
        });
        cardButtonsHBox.setSpacing(20);
        cardButtonsHBox.setPadding(new Insets(20));

        //layout of the game screen
        bottomButtons.getChildren().addAll(stop, collect, bet, doubleButton, play);
        bottomButtons.setSpacing(20);
        bottomButtons.setPadding(new Insets(20));
        gameLayout.setBottom(bottomButtons);
        gameLayout.setCenter(cardButtonsHBox);
        
        game = new Scene(gameLayout, width, height);
        stop.setOnMouseClicked(stopClicked -> {
            if (!logic.firstDealDone) { //can stop playing only if round is not going on
                //log out the user!
                primaryStage.setScene(frontPage);
            }
        });

        //PLAY
        play.setOnMouseClicked(playClicked -> {
            if (!logic.firstDealDone) { //new round
                if (logic.winnings >= logic.bet) { //can play if credit >= bet
                    logic.winnings -= logic.bet;
                    winningsLabel.setText("credits: " + logic.winnings / 100);
                    logic.playFresh();
                }
            } else { //continue round
                logic.playContinue();
                winningsLabel.setText("credits: " + logic.winnings / 100);
            }
        });

        //BET
        bet.setOnMouseClicked(betClicked -> {
            if (!logic.firstDealDone) { //bet can only be changed if there
                //is not an existing game round going
                logic.changeBet();
                bet.setText("bet: " + logic.bet / 100);
            }
            
        });
        
        for (int i = 0; i < 5; i++) {
            int whichButtonWasClicked = i;
            logic.cardButtons.get(i).setOnMouseClicked(klik -> logic.cardClicked(whichButtonWasClicked));
        }

        //final data for the app to start
        primaryStage.setScene(frontPage); //app starts with frontpage scene
        primaryStage.setTitle("Poker application by Henrik Hirvonen");
        primaryStage.show();
    }
    
}
