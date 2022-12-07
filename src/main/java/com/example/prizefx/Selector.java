package com.example.prizefx;

import java.util.ArrayList;
import java.util.Random;

public class Selector
{
    Random ran = new Random();
    public static void main(String[] args)
    {

    }

    public String nameSelect (ArrayList<String> names, String[] doneNames)
    {
        String winner = new String();
        int x = ran.nextInt(names.size());
        while(doneNames[x] == "1")
        {
            x= ran.nextInt(names.size());
        }

        winner = names.get(x);
        doneNames[x] = "1";

        return winner;
    }

    public String prizeSelect (ArrayList<String> prizes, String[] donePrizes)
    {
        String winner = new String();
        int x = ran.nextInt(prizes.size());
        while(donePrizes[x] == "1")
        {
            x= ran.nextInt(prizes.size());
        }

        winner = prizes.get(x);
        donePrizes[x] = "1";

        return winner;
    }
}
