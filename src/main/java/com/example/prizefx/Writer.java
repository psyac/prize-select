package com.example.prizefx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer
{
    public void write (ArrayList<String> winners) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter
                ("Winners.txt", false));
        for(int i = 0; i < winners.size() ; i++)
        {
            writer.append(winners.get(i));
            writer.newLine();
        }
        writer.close();
    }


}
