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
     * date    ____  Mon Mar 20 11:21:28 IST 2017
     * ls      ____  acct,bin,cache,charger,config,cust,d,data...... and many more
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal_commands);
        try {
            Process p = Runtime.getRuntime().exec("pwd");
            Scanner sc  = new Scanner(new InputStreamReader(p.getInputStream()));
            while(sc.hasNext())
            System.out.println(sc.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
