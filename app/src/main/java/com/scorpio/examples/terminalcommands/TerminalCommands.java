package com.scorpio.examples.terminalcommands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scorpio.examples.R;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TerminalCommands extends AppCompatActivity {

    /**
     * Some of the terminal commands that are working on my device
     * command ____ return
     *
     * date    ____  Mon Mar 20 11:21:28 *** 2017
     * ls      ____  acct,bin,cache,charger,config,cust,d,data...... and many more
     *
     *//**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal_commands);
        System.out.println("....................");
        try {
            Process p = Runtime.getRuntime().exec("for i in `seq 1 5`; do echo\"Hello\" ;done");
            Scanner scInput  = new Scanner(new InputStreamReader(p.getInputStream()));
            while(scInput.hasNext())
            System.out.println(scInput.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
