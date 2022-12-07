package com.example.prizefx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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
    public Boolean full = false;
    Label winner = new Label("Winner: ");
    Label prize = new Label("Prize: ");
    Button reroll = new Button("Re roll");
    Button outPut = new Button("OUT");

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("hello-view.fxml"));

        doneFill();
        stage.setFullScreen(true);

        winner.setFont(Font.font("Veranda", FontWeight.BOLD, 100));
        prize.setFont(Font.font("Veranda", FontWeight.BOLD, 100));
        reroll.setPrefSize(100,50);
        outPut.setVisible(false);

        BorderPane borderP = new BorderPane();
        borderP.setMinSize(1000, 500);
        borderP.setPadding(new Insets(10, 10, 10, 10));

        VBox text = new VBox(winner, prize);
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
                if(checkFull())
                {
                    winner.setText("OUT OF");
                    prize.setText("PRIZES");
                    outPut.setVisible(true);
                }
                else
                {
                    String[] winString = selectorGet();
                    winner.setText("Winner: "+ winString[0]);
                    prize.setText("Prize: "+ winString[1]);

                    String winCon = winString[0] + "," + winString[1];
                    winners.add(winCon);
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