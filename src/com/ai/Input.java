package com.ai;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    public static String readInputLine(String prompt)
    {

        String inString = "";
        try
        {
            // create buffered reader instance
            BufferedReader ibr = new BufferedReader( new InputStreamReader(System.in));
            while (inString.equals(""))
            {
                System.out.print(prompt);
                inString = ibr.readLine();
            }

        }

        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return inString;
    }

}
