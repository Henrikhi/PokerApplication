package pokerPackage;

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
    private boolean firstDealDone = false;
    private int winnings = 20;
    private int bet = 1;
    private ArrayList<Button> cardButtons;
    private boolean[] lockedCards = new boolean[5];
    
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
        Hand hand = new Hand(); //initialize the hand

        BorderPane gameLayout = new BorderPane();
        HBox bottomButtons = new HBox();
        HBox cardButtonsHBox = new HBox();
        Label winningsLabel = new Label("credits: " + this.winnings);
        winningsLabel.setPadding(new Insets(20));
        gameLayout.setTop(winningsLabel);

        //bottombuttons
        Button stop = new Button("stop");
        Button collect = new Button("collect");
        Button bet = new Button("bet");
        Button doubleButton = new Button("double");
        Button play = new Button("play");

        //visual cards
        this.cardButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.cardButtons.add(new Button());
        }
        
        cardButtons.forEach(card -> {
            card.setPrefSize(width / 10, height / 5);
            cardButtonsHBox.getChildren().add(card);
        });
        cardButtonsHBox.setSpacing(20);
        cardButtonsHBox.setPadding(new Insets(20));

        //layout
        bottomButtons.getChildren().addAll(stop, collect, bet, doubleButton, play);
        bottomButtons.setSpacing(20);
        bottomButtons.setPadding(new Insets(20));
        gameLayout.setBottom(bottomButtons);
        gameLayout.setCenter(cardButtonsHBox);
        
        game = new Scene(gameLayout, width, height);
        stop.setOnMouseClicked(stopClicked -> {
            //log out the user!
            primaryStage.setScene(frontPage);
        });
        play.setOnMouseClicked(playClicked -> {
            if (firstDealDone == false) { //fresh game
                this.winnings -= this.bet;
                winningsLabel.setText("credits: " + this.winnings);
                hand.emptyHand();
                hand.deal5();
                ArrayList<Card> handData = hand.getHand();
                for (int i = 0; i < 5; i++) {
                    Card card = handData.get(i);
                    cardButtons.get(i).setText(card.toString());
                }
                firstDealDone = true;
                //now the 5 cards are visible and the player can choose
                //which cards to lock.

            } else {
                firstDealDone = false;
                
                for (int i = 0; i < 5; i++) {
                    if (!lockedCards[i]) {
                        hand.replace(i);
                        Card newCard = hand.getCard(i);
                        cardButtons.get(i).setText(newCard.toString());
                    }
                    lockedCards[i] = false;
                    unlockCard(i);                    
                }
                
                int latestWin = hand.checkHand() * this.bet;
                this.winnings += latestWin;
                winningsLabel.setText("credits: " + this.winnings);
            }
            
        });
        
        for (int i = 0; i < 5; i++) {
            int whichButtonWasClicked = i;
            this.cardButtons.get(i).setOnMouseClicked(klik -> cardClicked(whichButtonWasClicked));
        }

        //final data for the app to start
        primaryStage.setScene(frontPage); //app starts with frontpage scene
        primaryStage.setTitle("Poker application by Henrik Hirvonen");
        primaryStage.show();
    }
    
    private void cardClicked(int i) {
        if (lockedCards[i] == false && firstDealDone) {
            lockCard(i);
        } else {
            unlockCard(i);
        }
    }
    
    private void lockCard(int i) {
        lockedCards[i] = true;
        this.cardButtons.get(i).setScaleX(0.8);
        this.cardButtons.get(i).setScaleY(0.8);
    }
    
    private void unlockCard(int i) {
        lockedCards[i] = false;
        this.cardButtons.get(i).setScaleX(1);
        this.cardButtons.get(i).setScaleY(1);
    }
    
}
