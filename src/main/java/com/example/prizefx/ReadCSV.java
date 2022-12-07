package com.example.prizefx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadCSV
{

    public ArrayList<String> getNames ()
    {
        ArrayList<String> names = new ArrayList<String>();
        String line = "";
        String splitBy = ",";
        int i = 0;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("Names.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] cell = line.split(splitBy);    // use comma as separato
                i++;
                names.add(i + " " + cell[0]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return names;
    }

    public ArrayList<String> getPrizes()
    {
        ArrayList<String> prizes = new ArrayList<String>();
        String line = "";
        String splitBy = ",";
        int i = 0;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("Prizes" +
                    ".csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] cell = line.split(splitBy);    // use comma as separato
                i++;
                prizes.add(i + " " + cell[0]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return prizes;
    }

}
