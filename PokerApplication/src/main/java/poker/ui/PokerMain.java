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
import poker.database.User;
import poker.logic.GameLogics;

public class PokerMain extends Application {

    private Scene frontPage;
    private Scene game;

    private final int width = 800;
    private final int height = 600;

    private GameLogics logic = new poker.logic.GameLogics();

    private boolean doubleOrCollect = false;
    private boolean doubleClicked = false;

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

        //primary game screen
        BorderPane gameLayout = new BorderPane();
        HBox bottomButtons = new HBox();
        HBox cardButtonsHBox = new HBox();
        VBox centerLayout = new VBox();
        BorderPane bottomLayout = new BorderPane();

        Label winningsLabel = new Label("credits: " + logic.player.getWinnings() / 100 + "€");
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

        cardButtonsHBox.setSpacing(20);

        Text roundInfoText = new Text("\n\n");
        roundInfoText.setFont(new Font(20));

        //layout of the game screen
        bottomButtons.getChildren().addAll(stop, bet, collect, doubleButton, play);
        bottomButtons.setSpacing(30);
        bottomLayout.setLeft(bottomButtons);
        bottomLayout.setRight(insertCoin);
        bottomLayout.setPadding(defaultInsets);
        gameLayout.setBottom(bottomLayout);
        cardButtonsHBox.setAlignment(Pos.CENTER);
        centerLayout.getChildren().addAll(roundInfoText, cardButtonsHBox);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setPadding(defaultInsets);
        gameLayout.setCenter(centerLayout);

        game = new Scene(gameLayout, width, height);
        stop.setOnMouseClicked(stopClicked -> {
            if (!logic.firstDealDone && !this.doubleOrCollect) { //can stop playing only if round is not going on
                //and if player is not in "double or collect"-situation
                logic.logOutPlayer();
                primaryStage.setScene(frontPage);
            }
        });

        //data for doubling
        VBox doublingLayout = new VBox();
        Text doublingText = new Text();
        doublingText.setFont(new Font(20));
        HBox doublingCardAndButtons = new HBox();
        VBox doublingButtons = new VBox();
        Button buttonHigh = new Button("High");
        Button buttonLow = new Button("Low");
        buttonHigh.setPrefSize(90, 70);
        buttonHigh.setShape(buttonShape);
        buttonHigh.setFont(new Font("Arial Bold", 15));
        buttonLow.setPrefSize(90, 70);
        buttonLow.setShape(buttonShape);
        buttonLow.setFont(new Font("Arial Bold", 15));
        Button doublingCard = new Button();
        doublingCard.setPrefSize(width / 7, height / 3);
        doublingCard.setFont(new Font("Arial", 32));
        doublingButtons.getChildren().addAll(buttonHigh, buttonLow);
        doublingCardAndButtons.getChildren().addAll(doublingCard, doublingButtons);
        doublingLayout.getChildren().addAll(doublingText, doublingCardAndButtons);
        doublingButtons.setSpacing(20);
        doublingButtons.setPadding(defaultInsets);
        doublingCardAndButtons.setAlignment(Pos.CENTER);
        doublingCardAndButtons.setSpacing(20);
        doublingCardAndButtons.setPadding(defaultInsets);
        doublingLayout.setAlignment(Pos.CENTER);
        doublingLayout.setPadding(defaultInsets);
        doublingLayout.setSpacing(20);

        //card graphics
        for (int i = 0; i < 5; i++) {
            logic.cardButtons[i] = new Button();
            Button card = logic.cardButtons[i];
            card.setPrefSize(width / 7, height / 3);
            cardButtonsHBox.getChildren().add(card);
            card.setFont(new Font("Arial", 32));
        }

        //CARD IS CLICKED
        for (int i = 0; i < 5; i++) {
            int whichButtonWasClicked = i;
            logic.cardButtons[i].setOnMouseClicked(klik -> logic.cardClicked(whichButtonWasClicked));
        }

        //PLAY
        play.setOnMouseClicked(playClicked -> {
            if (!this.doubleOrCollect) {
                if (!logic.firstDealDone) { //new round
                    gameLayout.setCenter(centerLayout);
                    roundInfoText.setText("\n Good luck! \n");
                    if (logic.player.getWinnings() >= logic.bet) { //can play if credit >= bet
                        logic.newRound();
                        winningsLabel.setText("credits: " + logic.player.getWinnings() / 100 + "€");
                        logic.playNewRound();
                    } else {
                        roundInfoText.setText("You do not have enough credits to continue. \n"
                                + "Please lower your bet or insert more coins. \n");
                    }
                } else { //continue round
                    double latestWin = logic.playContinueRound();
                    if (latestWin != 0) {
                        this.doubleOrCollect = true;
                        roundInfoText.setText("Congratulations! You have won " + logic.latestWin / 100 + "€!\n"
                                + "Please collect your winnings or try your luck in doubling them!\n");
                    } else {
                        roundInfoText.setText("\nBetter luck next time!\n");
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

        //INSERT COIN
        insertCoin.setOnMouseClicked(insertCoinClicked -> {
            logic.insertCoinClicked();
            winningsLabel.setText("credits: " + logic.player.getWinnings() / 100 + "€");
        });

        //COLLECT
        collect.setOnMouseClicked(collectClicked -> {
            if (this.doubleOrCollect && !this.doubleClicked) {
                this.doubleOrCollect = false;
                roundInfoText.setText("\nYou collected " + logic.latestWin / 100 + "€.\n");
                doublingText.setText("You collected " + logic.latestWin / 100 + "€. \n");
                logic.addWinnings();
                winningsLabel.setText("credits: " + logic.player.getWinnings() / 100 + "€");
            }
        });

        //DOUBLE
        doubleButton.setOnMouseClicked(doubleClicked -> {
            if (this.doubleOrCollect) {
                doublingCard.setText("");
                doublingText.setText("You are trying to double " + logic.latestWin / 100 + "€.\n"
                        + "Choose either \"high\" or \"low\".");
                gameLayout.setCenter(doublingLayout);
                this.doubleClicked = true;
            }
        });

        //HIGH CARD CHOSEN
        buttonHigh.setOnMouseClicked(highClicked -> {
            if (doubleClicked) {
                logic.newDoublingCard();
                double win = logic.highClicked();
                doublingCard.setText(logic.doublingCard.toString());
                doublingCard.setTextFill(logic.getColor(logic.doublingCard));
                if (win == 0) {
                    doublingText.setText("Too bad. Better luck next time! \n");
                    this.doubleClicked = false;
                    this.doubleOrCollect = false;
                } else if (win == 1) {
                    doubleClicked = false;
                    doublingText.setText("Red 7! You get to keep your winnings. \n"
                            + "You can collect your winnings or try to double " + logic.latestWin / 100 + "€ again.");
                } else if (win == 2) {
                    doubleClicked = false;
                    doublingText.setText("Congratulations! Doubling succesfull. \n"
                            + "You can collect your winnings or try to double " + logic.latestWin / 100 + "€ again.");
                }
            }
        });

        //LOW CARD CHOSEN
        buttonLow.setOnMouseClicked(lowClicked -> {
            if (doubleClicked) {
                logic.newDoublingCard();
                double win = logic.lowClicked();
                doublingCard.setText(logic.doublingCard.toString());
                doublingCard.setTextFill(logic.getColor(logic.doublingCard));
                if (win == 0) {
                    doublingText.setText("Too bad. Better luck next time! \n");
                    this.doubleClicked = false;
                    this.doubleOrCollect = false;
                } else if (win == 1) {
                    doubleClicked = false;
                    doublingText.setText("Red 7! You get to keep your winnings. \n"
                            + "You can collect your winnings or try to double " + logic.latestWin / 100 + "€ again.");
                } else if (win == 2) {
                    doubleClicked = false;
                    doublingText.setText("Congratulations! Doubling succesfull. \n"
                            + "You can collect your winnings or try to double " + logic.latestWin / 100 + "€ again.");
                }
            }
        });

        createAccount.setOnMouseClicked(createAccountClicked -> {
            User user = new User(accountName.getText(), password.getText());
            if (!logic.userExists(user)) {
                logic.createUser(user);
                System.out.println("account is created"); //this in ui
            } else {
                System.out.println("account already existed"); //this in ui
            }
        });

        logIn.setOnMouseClicked(logInClicked -> {
            User user = new User(accountName.getText(), password.getText());
            if (logic.logInOK(user)) {
                
                winningsLabel.setText("credits: " + logic.player.getWinnings() / 100 + "€");
                bet.setText("bet: " + logic.bet / 100 + "€");
                roundInfoText.setText("\nWelcome " + logic.player.getUserName() + "! \n");
                gameLayout.setCenter(centerLayout);
                primaryStage.setScene(game);
            } else {
                System.out.println("log in not succesful"); //this in ui
            }
        });

        //final data for the app to start
        primaryStage.setScene(frontPage); //app starts with frontpage scene
        primaryStage.setTitle("Poker application by Henrik Hirvonen");
        primaryStage.show();
    }

}
