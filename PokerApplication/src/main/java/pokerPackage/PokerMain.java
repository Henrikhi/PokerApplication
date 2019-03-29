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
    private boolean firstLocked = false;
    private boolean secondLocked = false;
    private boolean thirdLocked = false;
    private boolean fourthLocked = false;
    private boolean fifthLocked = false;
    private int winnings = 20;
    private int bet = 1;

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
        ArrayList<Button> cardButtons = new ArrayList<>();
        Button firstCardButton = new Button();
        Button secondCardButton = new Button();
        Button thirdCardButton = new Button();
        Button fourthCardButton = new Button();
        Button fifthCardButton = new Button();
        cardButtons.add(firstCardButton);
        cardButtons.add(secondCardButton);
        cardButtons.add(thirdCardButton);
        cardButtons.add(fourthCardButton);
        cardButtons.add(fifthCardButton);
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

                if (firstLocked == false) {
                    hand.replace(0);
                    Card newCard = hand.getCard(0);
                    firstCardButton.setText(newCard.toString());
                }
                if (secondLocked == false) {
                    hand.replace(1);
                    Card newCard = hand.getCard(1);
                    secondCardButton.setText(newCard.toString());
                }
                if (thirdLocked == false) {
                    hand.replace(2);
                    Card newCard = hand.getCard(2);
                    thirdCardButton.setText(newCard.toString());
                }
                if (fourthLocked == false) {
                    hand.replace(3);
                    Card newCard = hand.getCard(3);
                    fourthCardButton.setText(newCard.toString());
                }
                if (fifthLocked == false) {
                    hand.replace(4);
                    Card newCard = hand.getCard(4);
                    fifthCardButton.setText(newCard.toString());
                }

                firstLocked = false;
                secondLocked = false;
                thirdLocked = false;
                fourthLocked = false;
                fifthLocked = false;
                firstCardButton.setScaleX(1);
                firstCardButton.setScaleY(1);
                secondCardButton.setScaleX(1);
                secondCardButton.setScaleY(1);
                thirdCardButton.setScaleX(1);
                thirdCardButton.setScaleY(1);
                fourthCardButton.setScaleX(1);
                fourthCardButton.setScaleY(1);
                fifthCardButton.setScaleX(1);
                fifthCardButton.setScaleY(1);

                int latestWin = hand.checkHand() * this.bet;
                this.winnings += latestWin;
                winningsLabel.setText("credits: " + this.winnings);
            }

        });

        firstCardButton.setOnMouseClicked(firstClicked -> {
            if (firstLocked == false && firstDealDone) {
                firstLocked = true;
                firstCardButton.setScaleX(0.8);
                firstCardButton.setScaleY(0.8);
            } else {
                firstLocked = false;
                firstCardButton.setScaleX(1);
                firstCardButton.setScaleY(1);
            }
        });

        secondCardButton.setOnMouseClicked(secondClicked -> {
            if (secondLocked == false && firstDealDone) {
                secondLocked = true;
                secondCardButton.setScaleX(0.8);
                secondCardButton.setScaleY(0.8);
            } else {
                secondLocked = false;
                secondCardButton.setScaleX(1);
                secondCardButton.setScaleY(1);
            }
        });

        thirdCardButton.setOnMouseClicked(thirdClicked -> {
            if (thirdLocked == false && firstDealDone) {
                thirdLocked = true;
                thirdCardButton.setScaleX(0.8);
                thirdCardButton.setScaleY(0.8);
            } else {
                thirdLocked = false;
                thirdCardButton.setScaleX(1);
                thirdCardButton.setScaleY(1);
            }
        });

        fourthCardButton.setOnMouseClicked(fourthClicked -> {
            if (fourthLocked == false && firstDealDone) {
                fourthLocked = true;
                fourthCardButton.setScaleX(0.8);
                fourthCardButton.setScaleY(0.8);
            } else {
                fourthLocked = false;
                fourthCardButton.setScaleX(1);
                fourthCardButton.setScaleY(1);
            }
        });

        fifthCardButton.setOnMouseClicked(fifthClicked -> {
            if (fifthLocked == false && firstDealDone) {
                fifthLocked = true;
                fifthCardButton.setScaleX(0.8);
                fifthCardButton.setScaleY(0.8);
            } else {
                fifthLocked = false;
                fifthCardButton.setScaleX(1);
                fifthCardButton.setScaleY(1);
            }
        });

        //final data for the app to start
        primaryStage.setScene(frontPage); //app starts with frontpage scene
        primaryStage.setTitle("Poker application by Henrik Hirvonen");
        primaryStage.show();
    }

}
