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
    public Boolean wOrP = false;
    Label winner = new Label("");
    Label nameW = new Label("CHRISTMAS DRAW");
    Label ticket = new Label("2022");
    Label prize = new Label("");
    Button reroll = new Button("Re roll");
    Button outPut = new Button("OUT");

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("hello-view.fxml"));

        Image back = new Image("C:\\Users\\Aidan\\Desktop\\Personal-Projects\\prize-select\\src\\main\\resources\\com\\example\\prizefx\\background.png");
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
        nameW.setTextFill(Color.WHITE);
        ticket.setTextFill(Color.WHITE);
        winner.setTextFill(Color.WHITE);
        prize.setTextFill(Color.WHITE);
        reroll.setPrefSize(100,50);
        outPut.setVisible(false);

        BorderPane borderP = new BorderPane();
        borderP.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        borderP.setBackground(new Background(backIm));

        borderP.setMinSize(1000, 500);
        borderP.setPadding(new Insets(10, 10, 10, 10));

        VBox text = new VBox(winner, nameW, ticket, prize);
        text.setAlignment(Pos.CENTER);
        text.setSpacing(30);

        VBox buttons = new VBox(reroll, outPut);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(30);

        borderP.setCenter(text);
        borderP.setBottom(buttons);
        Scene scene = new Scene(borderP);

        buttonSetUp();

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void buttonSetUp()
    {

        reroll.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
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
                    String winCon = winString[0] + "," + winString[1];
                    winners.add(winCon);
                }
                else
                {

                    if(!wOrP)
                    {
                        winString = selectorGet();
                        fadeW.play();
                        fadeT.play();
                        fadeN.play();
                        String[] parts = winString[0].split(" ",2);
                        winner.setText("WINNER");
                        nameW.setText(parts[1]);
                        ticket.setText("Ticket: "+ parts[0]);
                        prize.setText("");
                        wOrP = true;
                    }
                    else
                    {
                        fadeP.play();
                        prize.setText("Prize: "+ winString[1]);
                        String winCon = winString[0] + "," + winString[1];
                        winners.add(winCon);
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