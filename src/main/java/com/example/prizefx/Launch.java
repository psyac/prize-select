package com.example.prizefx;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Launch extends Application
{
    public Selector select = new Selector();
    public ArrayList<String> winners = new ArrayList<String>();
    public ArrayList<String> names = new ReadCSV().getNames();;
    public ArrayList<String> prizes = new ReadCSV().getPrizes();
    public String[] doneNames = new String[names.size()];
    public String[] donePrizes = new String[prizes.size()];
    public String[] winString = new String[2];
    public String[] prevNames = {"","","",""};
    public String prevNamesString = "";
    public Boolean wOrP = false;
    public Boolean isFull = false;
    Label winner = new Label("ANNUAL");
    Label nameW = new Label("CLUB LOTTO");
    Label ticket = new Label("2023");
    Label prize = new Label("");
    Label buffer = new Label("");
    Label nameList = new Label ("");
    Button reroll = new Button("Re roll");
    Button outPut = new Button("OUT");
    String[] parts = {"","",""};

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("hello-view.fxml"));

        Image back = new Image("C:\\Users\\phil\\OneDrive\\Desktop\\prize-select\\src\\main\\resources\\com\\example\\prizefx\\background.png");
        BackgroundImage backIm = new BackgroundImage
                (back,
                        BackgroundRepeat.SPACE, BackgroundRepeat.SPACE,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT
                );


        doneFill();
        stage.setFullScreen(true);

        winner.setFont(Font.font("Veranda", FontWeight.BOLD, 150));
        ticket.setFont(Font.font("Veranda", FontWeight.BOLD, 100));
        nameW.setFont(Font.font("Veranda", FontWeight.BOLD, 100));
        prize.setFont(Font.font("Veranda", FontWeight.BOLD, 100));
        nameList.setFont(Font.font("Veranda", FontWeight.BOLD, 30));
        nameW.setTextFill(Color.WHITE);
        ticket.setTextFill(Color.WHITE);
        winner.setTextFill(Color.WHITE);
        prize.setTextFill(Color.WHITE);
        nameList.setTextFill(Color.WHITE);
        reroll.setPrefSize(100,50);
        outPut.setVisible(false);
        reroll.setStyle("-fx-background-color: #000000; ");

        BorderPane borderP = new BorderPane();
        borderP.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        borderP.setBackground(new Background(backIm));

        borderP.setMinSize(1000, 500);
        borderP.setPadding(new Insets(10, 10, 10, 10));

        VBox text = new VBox(winner, nameW, ticket, prize, buffer);
        text.setAlignment(Pos.CENTER);
        text.setSpacing(20);

        VBox buttons = new VBox(reroll, outPut, nameList);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(30);

        borderP.setCenter(text);
        borderP.setBottom(buttons);
        Scene scene = new Scene(borderP);

        buttonSetUp(stage);

        stage.setTitle("Christmas Prize Draw");
        stage.setScene(scene);
        stage.show();
    }

    public void buttonSetUp(Stage stage)
    {

        reroll.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                stage.setFullScreen(true);
                FadeTransition fadeW = new FadeTransition();
                fadeW.setDuration(Duration.seconds(3));
                fadeW.setFromValue(0);
                fadeW.setToValue(10);
                fadeW.setNode(winner);

                FadeTransition fadeN = new FadeTransition();
                fadeN.setDuration(Duration.seconds(3));
                fadeN.setFromValue(0);
                fadeN.setToValue(10);
                fadeN.setNode(nameW);

                FadeTransition fadeT = new FadeTransition();
                fadeT.setDuration(Duration.seconds(3));
                fadeT.setFromValue(0);
                fadeT.setToValue(10);
                fadeT.setNode(ticket);

                FadeTransition fadeP = new FadeTransition();
                fadeP.setDuration(Duration.seconds(3));
                fadeP.setFromValue(0);
                fadeP.setToValue(10);
                fadeP.setNode(prize);

                FadeTransition fadeNL = new FadeTransition();
                fadeNL.setDuration(Duration.seconds(3));
                fadeNL.setFromValue(0);
                fadeNL.setToValue(10);
                fadeNL.setNode(nameList);



                if(checkFull())
                {
                    fadeW.play();
                    fadeT.play();
                    fadeP.play();
                    fadeW.play();
                    winner.setText("");
                    nameW.setText("OUT");
                    ticket.setText("OF");
                    prize.setText("PRIZES");
                    outPut.setVisible(true);
                    if(!isFull)
                    {
                        String winCon = winString[0] + "," + winString[1];
                        winners.add(winCon);
                        isFull = true;
                    }

                }
                else
                {

                    if(!wOrP)
                    {
                        winString = selectorGet();
                        fadeW.play();
                        fadeT.play();
                        fadeN.play();
                        String[] nameParts = winString[0].split(" ",2);
                        winner.setText("WINNER");
                        nameW.setText(nameParts[1]);
                        ticket.setText("Number: "+ nameParts[0]);
                        parts[0] = nameParts[0];
                        parts[1] = nameParts[1];

                        prize.setText("");

                        wOrP = true;
                    }
                    else
                    {
                        fadeP.play();
                        prize.setText("Prize: "+ winString[1]);
                        String winCon = winString[0] + "," + winString[1];
                        winners.add(winCon);
                        parts[2] = winString[1];

                        prevNames[3] = prevNames[2];
                        prevNames[2] = prevNames[1];
                        prevNames[1] = prevNames[0];
                        prevNames[0] = parts[0] + " " + parts[1] + " " + parts[2] + " |";
                        prevNamesString =
                                prevNames[0] + " " + prevNames[1] + " " + prevNames[2] + " " +prevNames[3];

                        fadeNL.play();
                        nameList.setText(prevNamesString);

                        wOrP = false;
                    }
                }
            }
        });

        outPut.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    new Writer().write(winners);
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public String[] selectorGet ()
    {
        String[] winString = new String[2];

        winString[0] = select.nameSelect(names, doneNames);
        winString[1] = select.nameSelect(prizes, donePrizes);

        return winString;
    }

    public Boolean checkFull()
    {
        for(int i = 0; i < donePrizes.length; i++)
        {
            if(donePrizes[i] == "0")
                return false;
        }
        return true;
    }

    public void doneFill()
    {
        for(int i = 0; i < donePrizes.length; i++)
        {
            donePrizes[i] = "0";
        }
        for(int i = 0; i < doneNames.length; i++)
        {
            doneNames[i] = "0";
        }
    }






    public static void main(String[] args)
    {
        launch();
    }
}