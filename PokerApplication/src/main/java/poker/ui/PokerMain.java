package poker.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PokerMain extends Application {

    private Scene frontPage;
    private Scene game;
    private Scene doubling;

    private final int width = 800;
    private final int height = 600;

    private poker.logic.GameLogics logic = new poker.logic.GameLogics();
    private boolean doubleOrCollect = false;

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
        accountName.setFont(new Font(15));
        TextField password = new TextField("password");
        password.setMaxWidth(width / 4);
        password.setFont(new Font(15));
        Button logIn = new Button("log in");
        logIn.setMaxWidth(width / 4);
        logIn.setFont(new Font(15));
        Button createAccount = new Button("create account");
        createAccount.setMaxWidth(width / 4);
        createAccount.setFont(new Font(15));
        Button exit = new Button("exit");
        exit.setMaxWidth(width / 4);
        exit.setFont(new Font(15));

        frontPageLayout.getChildren().addAll(accountName, password, logIn, createAccount, exit);
        frontPageLayout.setSpacing(20);
        frontPageLayout.setAlignment(Pos.CENTER);
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
        VBox centerLayout = new VBox();
        BorderPane bottomLayout = new BorderPane();

        Label winningsLabel = new Label("credits: " + logic.winnings / 100 + "€");
        winningsLabel.setFont(new Font(20));
        Insets defaultInsets = new Insets(20);
        winningsLabel.setPadding(defaultInsets);
        gameLayout.setTop(winningsLabel);

        //bottombuttons of the gaming screen
        Circle buttonShape = new Circle(20);
        Button stop = new Button("stop");
        Button collect = new Button("collect");
        Button bet = new Button("bet: " + logic.bet / 100 + "€");
        Button doubleButton = new Button("double");
        Button play = new Button("play");
        Button insertCoin = new Button("Insert 2€");

        ArrayList<Button> bottomButtonArrayList = new ArrayList<>();
        bottomButtonArrayList.add(stop);
        bottomButtonArrayList.add(collect);
        bottomButtonArrayList.add(bet);
        bottomButtonArrayList.add(doubleButton);
        bottomButtonArrayList.add(play);
        bottomButtonArrayList.add(insertCoin);

        bottomButtonArrayList.forEach(button -> {
            button.setPrefSize(90, 70);
            button.setShape(buttonShape);
            button.setFont(new Font("Arial Bold", 15));
        });

        //card graphics
        for (int i = 0; i < 5; i++) {
            logic.cardButtons[i] = new Button();
            Button card = logic.cardButtons[i];
            card.setPrefSize(width / 7, height / 3);
            cardButtonsHBox.getChildren().add(card);
            card.setFont(new Font("Arial", 32));
        }

        cardButtonsHBox.setSpacing(20);

        Text roundEndText = new Text("\n\n");
        roundEndText.setFont(new Font(20));

        //layout of the game screen
        bottomButtons.getChildren().addAll(stop, bet, collect, doubleButton, play);
        bottomButtons.setSpacing(30);
        bottomLayout.setLeft(bottomButtons);
        bottomLayout.setRight(insertCoin);
        bottomLayout.setPadding(defaultInsets);
        gameLayout.setBottom(bottomLayout);
        cardButtonsHBox.setAlignment(Pos.CENTER);
        centerLayout.getChildren().addAll(roundEndText, cardButtonsHBox);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setPadding(defaultInsets);
        gameLayout.setCenter(centerLayout);

        game = new Scene(gameLayout, width, height);
        stop.setOnMouseClicked(stopClicked -> {
            if (!logic.firstDealDone && !this.doubleOrCollect) { //can stop playing only if round is not going on
                //and if player is not in "double or collect"-situation
                //remember to log out the user!
                primaryStage.setScene(frontPage);
            }
        });

        //PLAY
        play.setOnMouseClicked(playClicked -> {
            if (!this.doubleOrCollect) {
                if (!logic.firstDealDone) { //new round
                    if (logic.winnings >= logic.bet) { //can play if credit >= bet
                        logic.newRound();
                        winningsLabel.setText("credits: " + logic.winnings / 100 + "€");
                        logic.playFresh();
                    }
                } else { //continue round
                    double latestWin = logic.playContinue();
                    if (latestWin != 0) {
                        this.doubleOrCollect = true;
                        roundEndText.setText("Congratulations! You have won " + logic.latestWin / 100 + "€!\n"
                                + "Please collect your winnings or try your luck in doubling them!\n");
                    }
                }
            }
        });

        //BET
        bet.setOnMouseClicked(betClicked -> {
            if (!logic.firstDealDone && !this.doubleOrCollect) { //bet can only be changed if there
                //is not an existing game round going
                logic.changeBet();
                bet.setText("bet: " + logic.bet / 100 + "€");
            }

        });

        //CARD IS CLICKED
        for (int i = 0; i < 5; i++) {
            int whichButtonWasClicked = i;
            logic.cardButtons[i].setOnMouseClicked(klik -> logic.cardClicked(whichButtonWasClicked));
        }

        //INSERT COIN
        insertCoin.setOnMouseClicked(insertCoinClicked -> {
            logic.insertCoinClicked();
            winningsLabel.setText("credits: " + logic.winnings / 100 + "€");
        });

        //DOUBLING SCENARIO 
        BorderPane doublinLayout = new BorderPane();

        //Player has won some money in the round and has to double or collect
        //DOUBLE
        doubleButton.setOnMouseClicked(doubleClicked -> {
            if (this.doubleOrCollect) {
                this.doubleOrCollect = false;
                logic.doubleSuccesful();
                logic.addWinnings();
                winningsLabel.setText("credits: " + logic.winnings / 100 + "€");
            }
        });

        //COLLECT
        collect.setOnMouseClicked(collectClicked -> {
            if (this.doubleOrCollect) {
                this.doubleOrCollect = false;
                logic.addWinnings();
                winningsLabel.setText("credits: " + logic.winnings / 100 + "€");
                roundEndText.setText("\n\n");
            }

        });

        //final data for the app to start
        primaryStage.setScene(frontPage); //app starts with frontpage scene
        primaryStage.setTitle("Poker application by Henrik Hirvonen");
        primaryStage.show();
    }

}
